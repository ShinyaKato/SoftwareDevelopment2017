import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Item extends Sprite {
  private int type;
  private static Random random = new Random(2398);

  public Item(int x, int y) {
    super(x, y);
    type = random.nextInt(2);
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
    if(type == 0) {
      g.setColor(Color.red);
    } else if(type == 1) {
      g.setColor(color.black);
    }
    g.fillOval(x * CELL_WIDTH, y * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
  }
}
