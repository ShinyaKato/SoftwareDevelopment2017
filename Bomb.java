import javax.swing.*;
import java.awt.*;

public class Bomb extends Sprite {
  Image image;
  public int count = 60;

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
    g.drawImage(image, x * CELL_WIDTH, y * CELL_HEIGHT, (x + 1) * CELL_WIDTH, (y + 1) * CELL_HEIGHT, ((60 - count) / 10) * CELL_WIDTH, 0, (((60 - count) / 10) + 1) * CELL_WIDTH, CELL_HEIGHT, null);
  }
}
