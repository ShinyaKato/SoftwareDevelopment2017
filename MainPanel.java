import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;

class MainPanel extends JPanel {
  private MainFrame frame;
  Clip sc_click, sc_cancel;

  public static Clip getClip(String filename) {
     Clip clip = null;
     try {
       AudioInputStream ais = AudioSystem.getAudioInputStream(new File(filename));
       clip = (Clip)AudioSystem.getLine(new Line.Info(Clip.class));
       clip.open(ais);
     } catch(Exception e) {
       System.out.println(e);
       System.exit(0);
     }
     return clip;
   }

  public MainPanel(MainFrame f) {
    frame = f;
    setBounds(0, 0, Sprite.SCREEN_WIDTH, Sprite.SCREEN_HEIGHT);

    sc_click = getClip("click.wav");
    sc_cancel = getClip("cancel.wav");

    JLabel text = new JLabel("BOMBER MAN");
    text.setForeground(Color.WHITE);
    text.setFont(new Font("Arial", Font.BOLD, 90));
    add(text);

    JButton wbutton = new JButton("Run server");
    wbutton.setFont(new Font("Arial", Font.PLAIN, 20));
    wbutton.setPreferredSize(new Dimension(300, 50));
    wbutton.setMargin(new Insets(20, 20, 20, 20));
    wbutton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        sc_click.setFramePosition(0);
        sc_click.start();
        frame.runServer();
      }
    });
    add(wbutton);

    JButton sbutton = new JButton("Connect to server");
    sbutton.setFont(new Font("Arial", Font.PLAIN, 20));
    sbutton.setPreferredSize(new Dimension(300, 50));
    sbutton.setMargin(new Insets(20, 20, 20, 20));
    sbutton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        sc_click.setFramePosition(0);
        sc_click.start();
        frame.tryConnection();
      }
    });
    add(sbutton);

    JButton qbutton = new JButton("Exit");
    qbutton.setFont(new Font("Arial", Font.PLAIN, 18));
    qbutton.setPreferredSize(new Dimension(250, 50));
    qbutton.setMargin(new Insets(20, 20, 20, 20));
    qbutton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        sc_cancel.setFramePosition(0);
        sc_cancel.start();
        frame.closeFrame();
      }
    });
    add(qbutton);

    ImageIcon bombicon = new ImageIcon("./bomb.png");
    JLabel bomb = new JLabel(bombicon);
    //bomb.setPreferredSize(new Dimension(450,300));
    bomb.setPreferredSize(new Dimension(470,220));
    bomb.setHorizontalAlignment(JLabel.CENTER);
    add(bomb);

    JLabel text2 = new JLabel("   one life, one chance ...");
    text2.setForeground(Color.GRAY);
    text2.setFont(new Font("Arial", Font.ITALIC, 22));
    add(text2);
    

  }
}

