import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Item extends Sprite {
  private Image image[] = new Image[2];
  private int type;
  private static Random random = new Random(2398);

  public Item(int x, int y) {
    super(x, y);
    type = random.nextInt(2);
    try {
      this.image[0] = new ImageIcon("./item_fire.png").getImage();
      this.image[1] = new ImageIcon("./item_bomb.png").getImage();
    } catch(Exception e) {}
  }

  public void update() {
  }

  public void activate(Player player) {
    if(type == 0) {
      player.addBombRange();
    } else if(type == 1) {
      player.addBombNumber();
    }
  }

  public void paint(Graphics g) {
    g.drawImage(image[type], x * CELL_WIDTH + 4, y * CELL_HEIGHT + 4, null);
  }
}
