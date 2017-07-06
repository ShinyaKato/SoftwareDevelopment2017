import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;

public class ClientPanel extends JPanel {
  private MainFrame frame;
  Clip sc_click, sc_cancel;

  private JTextField ipField;

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

  public ClientPanel(MainFrame f) {
    frame = f;
    setBounds(0, 0, Sprite.SCREEN_WIDTH, Sprite.SCREEN_WIDTH);

    sc_click = getClip("click.wav");
    sc_cancel = getClip("cancel.wav");

    JPanel cPanel = new JPanel(); //
    JPanel bottomPanel = new JPanel(); //


    JLabel text = new JLabel("\n\n対戦相手のマシン名(IPアドレス)を入力してください :");
    text.setFont(new Font("Arial", Font.PLAIN, 20));
    text.setForeground(Color.WHITE);
    cPanel.add(text);

    ipField = new JTextField(12);
    ipField.setPreferredSize(new Dimension(30, 40));
    ipField.setFont(new Font("Arial", Font.PLAIN, 24));
    cPanel.add(ipField);

    JButton sbutton = new JButton("入力完了、対戦開始！");
    sbutton.setFont(new Font("Arial", Font.PLAIN, 20));
    sbutton.setPreferredSize(new Dimension(350, 70));
    sbutton.setMargin(new Insets(20, 20, 20, 20));
    sbutton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        sc_click.setFramePosition(0);
        sc_click.start();
        frame.connect(ipField.getText());
      }
    });
    cPanel.add(sbutton);

    JButton cbutton = new JButton("キャンセル");
    cbutton.setFont(new Font("Arial", Font.PLAIN, 15));
    cbutton.setPreferredSize(new Dimension(100, 50));
    cbutton.setMargin(new Insets(20, 20, 20, 20));
    cbutton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        sc_cancel.setFramePosition(0);
        sc_cancel.start();
        frame.stopConnection();
      }
    });
    bottomPanel.add(cbutton);


    JPanel nullPanel = new JPanel();
    nullPanel.setPreferredSize(new Dimension(600, 100));
    nullPanel.setBackground(Color.BLACK);
    add(nullPanel, BorderLayout.PAGE_START);

    cPanel.setPreferredSize(new Dimension(600, 200));
    cPanel.setBackground(Color.BLACK);
    add(cPanel, BorderLayout.CENTER);

    bottomPanel.setPreferredSize(new Dimension(600, 100));
    bottomPanel.setBackground(Color.BLACK);
    add(bottomPanel, BorderLayout.PAGE_END);
    /*
    JButton button5 = new JButton("Button5");
    JButton button6 = new JButton("Button6");

    JPanel p3 = new JPanel();
    FlowLayout layout = new FlowLayout();
    layout.setAlignment(FlowLayout.RIGHT);
    p3.setLayout(layout);
    p3.add(button5);
    p3.add(button6);
    add(p3, BorderLayout.PAGE_END);
    */

  }
}
