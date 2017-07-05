import javax.swing.*;
import java.awt.*;

public class Bomb extends Sprite {
  Image image;
  public int count = 120;

  public Bomb(int x, int y) {
    super(x, y);
    try {
      this.image = new ImageIcon("./bomb_anim.png").getImage();
    } catch(Exception e) {}
  }

  public void update() {
    count--;
  }

  public boolean exploded() {
    return count <= 0;
  }

  public void paint(Graphics g) {
    int gx = (count > 30 ? 0 : (30 - count) / 5) * CELL_WIDTH;
    int gy = 0;
    int px = x * CELL_WIDTH;
    int py = y * CELL_HEIGHT;
    g.drawImage(image, px, py, px + CELL_WIDTH, py + CELL_HEIGHT, gx, gy, gx + CELL_WIDTH, gy + CELL_HEIGHT, null);
  }
}
