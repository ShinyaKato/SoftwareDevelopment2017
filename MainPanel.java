import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MainPanel extends JPanel {
  private MainFrame frame;

  public MainPanel(MainFrame f) {
    frame = f;
    setBounds(0, 0, Sprite.SCREEN_WIDTH, Sprite.SCREEN_HEIGHT);

    //Container titleCont = getContentPane();
    //titleCont.setLayout(null);
    JLabel text = new JLabel("BOMBER MAN");
    text.setForeground(Color.WHITE);
    text.setFont(new Font("Arial", Font.BOLD, 90));
    //text.setBounds(10,10,100,40);
    //text.setVerticalAlignment(JLabel.TOP);
    add(text);

    JButton wbutton = new JButton("対戦待ちをする");
    wbutton.setFont(new Font("Arial", Font.PLAIN, 20));
    wbutton.setPreferredSize(new Dimension(300, 50));
    wbutton.setMargin(new Insets(20, 20, 20, 20));
    wbutton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        frame.runServer();
      }
    });
    //wbutton.setForeground(Color.RED);
    //wbutton.setBackground(Color.RED);
    add(wbutton);

    JButton sbutton = new JButton("対戦相手を指定する");
    sbutton.setFont(new Font("Arial", Font.PLAIN, 20));
    sbutton.setPreferredSize(new Dimension(300, 50));
    sbutton.setMargin(new Insets(20, 20, 20, 20));
    sbutton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        frame.tryConnection();
      }
    });
    //sbutton.setForeground(Color.RED);
    //sbutton.setBackground(Color.RED);
    add(sbutton);

    JButton qbutton = new JButton("ゲームを終了する");
    qbutton.setFont(new Font("Arial", Font.PLAIN, 18));
    qbutton.setPreferredSize(new Dimension(250, 50));
    qbutton.setMargin(new Insets(20, 20, 20, 20));
    qbutton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        frame.closeFrame();
      }
    });
    //qbutton.setForeground(Color.RED);
    //qbutton.setBackground(Color.RED);
    add(qbutton);

    ImageIcon bombicon = new ImageIcon("./bomb.png");
    JLabel bomb = new JLabel(bombicon);
    bomb.setPreferredSize(new Dimension(450,300));
    bomb.setHorizontalAlignment(JLabel.CENTER);
    add(bomb);

  }
}

