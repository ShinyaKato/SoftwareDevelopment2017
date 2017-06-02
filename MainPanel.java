import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MainPanel extends JPanel {
  private MainFrame frame;

  public MainPanel(MainFrame f) {
    frame = f;
    setBounds(0, 0, 640, 480);

    JButton wbutton = new JButton("対戦待ちをする");
    wbutton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        frame.runServer();
      }
    });
    add(wbutton);

    JButton sbutton = new JButton("対戦相手を指定する");
    sbutton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        frame.tryConnection();
      }
    });
    add(sbutton);

    JButton qbutton = new JButton("ゲームを終了する");
    qbutton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        frame.closeFrame();
      }
    });
    add(qbutton);
  }
}

