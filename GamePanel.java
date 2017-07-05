import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

class GamePanel extends JPanel implements Runnable, KeyListener {
  public static long MSEC_PER_FRAME = 17;

  private MainFrame frame;

  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private KeyInput keyInput[] = new KeyInput[2];

  private Thread thread;

  private FieldCell cells[][] = new FieldCell[Sprite.WIDTH][Sprite.HEIGHT];
  private Player player[] = new Player[2];
  private int playerNo;

  private int counter;
  private int counter2;

  public static boolean gmoflg = false;
  public static boolean winflg = false;

  public GamePanel(MainFrame f) {
    frame = f;
    setBounds(0, 0, Sprite.SCREEN_WIDTH, Sprite.SCREEN_HEIGHT);
  }

  public void init(Socket s, int playerNo) {
    socket = s;
    try {
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
    } catch(IOException e) {}
    for(int i = 0; i < 2; i++) {
      keyInput[i] = new KeyInput(in, out);
    }

    this.playerNo = playerNo;
    player[0] = new Player(1, 1);
    player[1] = new Player(Sprite.WIDTH - 2, Sprite.HEIGHT - 2);

    for(int i = 0; i < Sprite.WIDTH; i++) {
      for(int j = 0; j < Sprite.HEIGHT; j++) {
        if(i == 0 || i == Sprite.WIDTH - 1 || j == 0 || j == Sprite.HEIGHT - 1 || (i % 2 == 0 && j % 2 == 0)) {
          cells[i][j] = new FieldWallCell(i, j);
        } else {
          cells[i][j] = new FieldFloorCell(i, j);
          if(!((i < 3 && j < 3) || (i > Sprite.WIDTH - 4 && j > Sprite.HEIGHT - 4))) {
            cells[i][j].set(new Block(i, j));
          }
        }
      }
    }
    cells[1][1].set(player[0]);
    cells[Sprite.WIDTH - 2][Sprite.HEIGHT - 2].set(player[1]);

    counter = 0;
    counter2 = 0;
  }

  public void start() {
    thread = new Thread(this);
    thread.start();
  }

  public void run(){
    while(true) {
      long begin = System.currentTimeMillis();

      keyInput[playerNo].send();
      for(int i = 0; i < 2; i++) {
        keyInput[i].clear();
      }
      for(int i = 0; i < 2; i++) {
        if(i != playerNo) {
          keyInput[i].recieve();
        }
      }

      updateGameState();
      repaint();

      try {
        long past = System.currentTimeMillis() - begin;
        long wait = MSEC_PER_FRAME - past;
        if(wait < 0) wait = 0;
        thread.sleep(wait);
      } catch(InterruptedException e) {}
      counter++;
    }
  }



  public void updateGameState() {
    for(int i = 0; i < Sprite.WIDTH; i++) {
      for(int j = 0; j < Sprite.HEIGHT; j++) {
        cells[i][j].update();
        Bomb bomb = cells[i][j].getBomb();
        Fire fire =cells[i][j].getFire();
        if(fire != null && fire.exploded()) {
          cells[i][j].remove(fire);
        }

        if(bomb != null && bomb.exploded()) {
          cells[i][j].remove(bomb);
          cells[i][j].set(new Fire(i, j));
          for(int n = 1; n < 5 ; n++) {
            if(i + n >= Sprite.WIDTH) break;
            Block block = cells[i + n][j].getBlock();
            if(block != null) {
              cells[i + n][j].remove(block);
              cells[i + n][j].set(new Fire(i + n, j));
              break;
            } else if(cells[i + n][j].whichType(i + n, j)) {
              cells[i + n][j].set(new Fire(i + n, j));
            } else {
              break;
            }
          }
          for(int n = 1; n < 5; n++){
            if(i - n <= 0) break;
            Block block = cells[i - n][j].getBlock();
            if(block != null) {
              cells[i - n][j].remove(block);
              cells[i - n][j].set(new Fire(i - n, j));
              break;
            } else if(cells[i - n][j].whichType(i - n, j)) {
              cells[i - n][j].set(new Fire(i - n, j));
            } else {
              break;
            }
          }
          for(int m = 1 ; m < 5; m++) {
            if(j + m >= Sprite.HEIGHT) break;
            Block block = cells[i][j + m].getBlock();
            if(block != null) {
              cells[i][j + m].remove(block);
              cells[i][j + m].set(new Fire(i, j + m));
              break;
            } else if(cells[i][j + m].whichType(i, j + m)){
              cells[i][j + m].set(new Fire(i, j + m));
            } else {
              break;
            }
          }
          for(int m = 1; m < 5 ; m++) {
            if(j - m <= 0) break;
            Block block = cells[i][j - m].getBlock();
            if(block != null) {
              cells[i][j - m].remove(block);
              cells[i][j - m].set(new Fire(i, j - m));
              break;
            } else if(cells[i][j - m].whichType(i,j - m)) {
              cells[i][j - m].set(new Fire(i, j - m));
            } else {
              break;
            }
          }
        }
      }
    }

    for(int i = 0; i < 2; i++) {
      player[i].update();

      int cx = player[i].currentX();
      int cy = player[i].currentY();

      int dir = keyInput[i].moveDirection();
      if(dir >= 0 && !player[i].isMoving()) {
        int nx = player[i].nextX(dir);
        int ny = player[i].nextY(dir);
        if(FieldCell.isin(nx, ny) && cells[nx][ny].canMove(nx, ny)) {
          player[i].moveTo(dir);
          cells[cx][cy].remove(player[i]);
          cells[nx][ny].set(player[i]);
        }
      }

      if(keyInput[i].bombDirection()) {
        Bomb bomb = new Bomb(cx, cy);
        cells[cx][cy].set(bomb);
      }
    }

    int mx = player[playerNo].currentX();
    int my = player[playerNo].currentY();
    int ox = player[1 - playerNo].currentX();
    int oy = player[1 - playerNo].currentY();
    if(cells[mx][my].getFire() != null && winflg == false) {
      gmoflg = true;
      cells[mx][my].getDead();
    } else if(cells[ox][oy].getFire()!=null && gmoflg==false) {
      winflg = true;
    }
    if(gmoflg == true || winflg == true) {
      counter2++;
      if(counter2 == 150) {
        System.exit(0);
      }
    }
  }

  public void paintComponent(Graphics g) {
    for(int i = 0; i < Sprite.WIDTH; i++) {
      for(int j = 0; j < Sprite.HEIGHT; j++) {
        cells[i][j].paint(g);
      }
    }

    for(int i = 0; i < 2; i++) {
      player[i].paint(g, frame);
    }

    if(gmoflg == true) {
      Graphics2D g2 = (Graphics2D) g;
      String text = "GAME OVER";
      Font font = new Font("Arial", Font.BOLD, 80);
      g2.setFont(font);
      g2.setColor(Color.red);
      g2.drawString(text, Sprite.SCREEN_WIDTH / 2 - 242, Sprite.SCREEN_HEIGHT / 2 + 20);
    } else if(winflg == true) {
      Graphics2D g2 = (Graphics2D) g;
      String text = "WINNER";
      Font font = new Font("Arial", Font.BOLD, 80);
      g2.setFont(font);
      g2.setColor(Color.green);
      g2.drawString(text, Sprite.SCREEN_WIDTH / 2 - 164, Sprite.SCREEN_HEIGHT / 2 + 20);
    }
  }

  public void keyPressed(KeyEvent e) {
    keyInput[playerNo].press(e.getKeyCode());
  }

  public void keyReleased(KeyEvent e) {}

  public void keyTyped(KeyEvent e) {}
}
