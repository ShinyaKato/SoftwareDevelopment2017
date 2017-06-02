import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClientPanel extends JPanel {
  private MainFrame frame;

  private JTextField ipField;

  public ClientPanel(MainFrame f) {
    frame = f;
    setBounds(0, 0, Sprite.SCREEN_WIDTH, Sprite.SCREEN_WIDTH);

    JLabel text = new JLabel("対戦相手のマシン名を入力してください");
    add(text);

    ipField = new JTextField(30);
    add(ipField);

    JButton sbutton = new JButton("対戦開始");
    sbutton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        frame.connect(ipField.getText());
      }
    });
    add(sbutton);

    JButton cbutton = new JButton("キャンセル");
    cbutton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        frame.stopConnection();
      }
    });
    add(cbutton);
  }
}
