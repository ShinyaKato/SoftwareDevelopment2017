import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;

public class ServerPanel extends JPanel {
  private MainFrame frame;
  Clip sc_cancel;

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

  public ServerPanel(MainFrame f) {
    frame = f;
    setBounds(0, 0, Sprite.SCREEN_WIDTH, Sprite.SCREEN_HEIGHT);

    sc_cancel = getClip("cancel.wav");

    JPanel cPanel = new JPanel(); //
    JPanel bottomPanel = new JPanel(); //

    JLabel text = new JLabel("接続を待機しています...");
    text.setForeground(Color.WHITE);
    text.setFont(new Font("Arial", Font.PLAIN, 20));
    cPanel.add(text);

    JButton cbutton = new JButton("キャンセル");
    cbutton.setFont(new Font("Arial", Font.PLAIN, 15));
    cbutton.setPreferredSize(new Dimension(120, 50));
    cbutton.setMargin(new Insets(20, 20, 20, 20));
    cbutton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        sc_cancel.setFramePosition(0);
        sc_cancel.start();
        frame.stopServer();
      }
    });
    bottomPanel.add(cbutton);

    JPanel nullPanel = new JPanel();
    nullPanel.setPreferredSize(new Dimension(600, 170));
    nullPanel.setBackground(Color.BLACK);
    add(nullPanel, BorderLayout.PAGE_START);

    cPanel.setPreferredSize(new Dimension(600, 50));
    cPanel.setBackground(Color.BLACK);
    add(cPanel, BorderLayout.CENTER);

    bottomPanel.setPreferredSize(new Dimension(600, 100));
    bottomPanel.setBackground(Color.BLACK);
    add(bottomPanel, BorderLayout.PAGE_END);

  }
}
