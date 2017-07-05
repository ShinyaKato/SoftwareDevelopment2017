import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Block extends Sprite {
  private Image image;
  private static Random random = new Random(123);

  public Block(int x, int y) {
    super(x, y);
    try {
      this.image = new ImageIcon("./block.png").getImage();
    } catch(Exception e) {}
  }

  public void update() {
  }

  public Item getItem() {
    if(random.nextInt(5) == 0) {
      return new Item(x, y);
    }
    return null;
  }

  public void paint(Graphics g) {
    g.drawImage(image, x * CELL_WIDTH, y * CELL_HEIGHT, null);
  }
}
