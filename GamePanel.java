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
        if(bomb != null && bomb.exploded()) {
          /* TODO: 爆風の広がり, ブロックが壊れる処理を実装する */
          cells[i][j].remove(bomb);
          cells[i][j].set(new Fire(i, j));
        }
      }
    }

    for(int i = 0; i < 2; i++) {
      int dir = keyInput[i].moveDirection();
      if(dir >= 0) {
        Point currentPos = player[i].currentPosition();
        Point nextPos = player[i].nextPosition(dir);
        if(FieldCell.isin(nextPos.x, nextPos.y) && cells[nextPos.x][nextPos.y].canMove(nextPos.x, nextPos.y)) {
          player[i].moveTo(nextPos.x, nextPos.y);
          cells[currentPos.x][currentPos.y].remove(player[i]);
          cells[nextPos.x][nextPos.y].set(player[i]);
        }
      }

      if(keyInput[i].bombDirection()) {
        Point currentPos = player[i].currentPosition();
        Bomb bomb = new Bomb(currentPos.x, currentPos.y);
        cells[currentPos.x][currentPos.y].set(bomb);
      }
    }

    // TODO: 勝敗判定
  }

  public void paintComponent(Graphics g) {
    for(int i = 0; i < Sprite.WIDTH; i++) {
      for(int j = 0; j < Sprite.HEIGHT; j++) {
        cells[i][j].paint(g);
      }
    }

    for(int i = 0; i < 2; i++) {
      player[i].paint(g);
    }
  }

  public void keyPressed(KeyEvent e) {
    keyInput[playerNo].press(e.getKeyCode());
  }

  public void keyReleased(KeyEvent e) {}

  public void keyTyped(KeyEvent e) {}
}
