import javax.swing.*;
import java.awt.*;

public class Player extends Sprite {
  Image image;
  int count = 0;
  int dir = 0;
  int px, py;

  public Player(int x, int y) {
    super(x, y);
    this.px = x * CELL_WIDTH;
    this.py = y * CELL_HEIGHT;
    try {
      this.image = new ImageIcon("./player.png").getImage();
    } catch(Exception e) {}
  }

  public void update() {
    if(count > 0) {
      count--;
      px += 4 * DIFF[dir];
      py += 4 * DIFF[dir + 1];
    }
  }

  public Point currentPosition() {
    return new Point(x, y);
  }

  public Point nextPosition(int dir) {
    return new Point(x + DIFF[dir], y + DIFF[dir + 1]);
  }

  public void moveTo(int x, int y, int dir) {
    if(count == 0) {
      count = 8;
      this.x = x;
      this.y = y;
      this.dir = dir;
    }
  }

  public void paint(Graphics g, JFrame frame) {
    int chipx[] = { 1, 2, 2, 2, 1, 1, 0, 0, 0 };
    int chipy[] = { 0, 2, 3, 1 };
    int gx = chipx[count] * CELL_WIDTH, gy = chipy[dir] * CELL_HEIGHT;
    g.drawImage(image, px, py, px + CELL_WIDTH, py + CELL_HEIGHT, gx, gy, gx + CELL_WIDTH, gy + CELL_HEIGHT, frame);
  }
}
