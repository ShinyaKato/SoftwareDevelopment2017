import javax.swing.*;
import java.awt.*;

public class Player extends Sprite {
  Image image;

  public Player(int x, int y) {
    super(x, y);
    try {
      this.image = new ImageIcon("./monster.gif").getImage();
    } catch(Exception e) {}
  }

  public Point currentPosition() {
    return new Point(x, y);
  }

  public Point nextPosition(int dir) {
    return new Point(x + DIFF[dir], y + DIFF[dir + 1]);
  }

  public void moveTo(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void paint(Graphics g, JFrame frame) {
    g.drawImage(image, x * CELL_WIDTH, y * CELL_HEIGHT, frame);
  }
}
