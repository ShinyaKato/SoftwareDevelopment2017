import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Item extends Sprite {
  private int type;
  private Random random = new Random();

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
}
