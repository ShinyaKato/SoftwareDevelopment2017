import javax.swing.*;
import java.awt.*;

public class Player extends Sprite {
  private static final int chipx[] = { 1, 2, 2, 2, 1, 1, 0, 0, 0 };
  private static final int chipy[] = { 0, 2, 3, 1 };

  private Image image;
  private int width = CELL_WIDTH, height = CELL_HEIGHT;
  private int count = 0;
  private int dir = 0;
  private int px, py;

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

  public boolean isMoving() {
    return count > 0;
  }

  public int currentX() {
    return x;
  }

  public int currentY() {
    return y;
  }

  public int nextX(int dir) {
    return x + DIFF[dir];
  }

  public int nextY(int dir) {
    return y + DIFF[dir + 1];
  }

  public void moveTo(int dir) {
    if(count == 0) {
      count = 8;
      this.dir = dir;
      this.x += DIFF[dir];
      this.y += DIFF[dir + 1];
    }
  }

  public void paint(Graphics g, JFrame frame) {
    int gx = chipx[count] * width;
    int gy = chipy[dir] * height;
    g.drawImage(image, px, py, px + width, py + height, gx, gy, gx + width, gy + height, frame);
  }
}
