import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ServerPanel extends JPanel {
  private MainFrame frame;

  public ServerPanel(MainFrame f) {
    frame = f;
    setBounds(0, 0, 640, 480);

    JLabel text = new JLabel("接続を待機しています");
    add(text);

    JButton cbutton = new JButton("キャンセル");
    cbutton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        frame.stopServer();
      }
    });
    add(cbutton);
  }
}
