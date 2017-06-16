import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class MainFrame extends JFrame{
  private static final int PORT = 8812;

  private MainPanel mainPanel = new MainPanel(this);
  private ServerPanel serverPanel = new ServerPanel(this);
  private ClientPanel clientPanel = new ClientPanel(this);
  private GamePanel gamePanel = new GamePanel(this);

  private ServerSocket serverSocket;
  private Socket server;
  private Socket client;

  public MainFrame(){
    super("ゲーム画面");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Container pane = getContentPane();

    mainPanel.setVisible(true);
    pane.add(mainPanel);
    pane.setPreferredSize(new Dimension(Sprite.SCREEN_WIDTH, Sprite.SCREEN_HEIGHT));

    serverPanel.setVisible(false);
    pane.add(serverPanel);

    clientPanel.setVisible(false);
    pane.add(clientPanel);

    gamePanel.setVisible(false);
    pane.add(gamePanel);
    addKeyListener(gamePanel);

    pack();
  }

  public void runServer() {
    mainPanel.setVisible(false);
    serverPanel.setVisible(true);

    new Thread() {
      public void run() {
        try {
          serverSocket = new ServerSocket(PORT);
          server = serverSocket.accept();
          System.out.println("Server Connection Established.");
        } catch(IOException e) {}
        gamePanel.init(server, 0);
        requestFocusInWindow();
        serverPanel.setVisible(false);
        gamePanel.setVisible(true);
        gamePanel.start();
      }
    }.start();
  }

  public void stopServer() {
    serverPanel.setVisible(false);
    mainPanel.setVisible(true);
  }

  public void tryConnection() {
    mainPanel.setVisible(false);
    clientPanel.setVisible(true);
  }

  public void connect(String name) {
    try {
      InetAddress address = InetAddress.getByName(name);
      client = new Socket(address, PORT);
      System.out.println("Client Connection Established.");
    } catch(IOException e) {}

    gamePanel.init(client, 1);
    requestFocusInWindow();
    clientPanel.setVisible(false);
    gamePanel.setVisible(true);
    gamePanel.start();
  }

  public void stopConnection() {
    clientPanel.setVisible(false);
    mainPanel.setVisible(true);
  }

  public void startGame() {
    mainPanel.setVisible(false);
    gamePanel.setVisible(true);
  }

  public void closeFrame() {
    System.exit(0);
  }
}
