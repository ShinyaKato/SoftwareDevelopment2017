import javax.swing.*;
import java.awt.*;

public class Block extends Sprite {
  Image image;

  public Block(int x, int y) {
    super(x, y);
    try {
      this.image = new ImageIcon("./block.png").getImage();
    } catch(Exception e) {}
  }

  public void update() {
  }

  public void paint(Graphics g) {
    g.drawImage(image, x * CELL_WIDTH, y * CELL_HEIGHT, null);
  }
}
