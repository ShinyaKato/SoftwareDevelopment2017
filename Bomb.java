import javax.swing.*;
import java.awt.*;

public class Bomb extends Sprite {
  public int count = 24;

  public Bomb(int x, int y) {
    super(x, y);
    this.color = Color.black;
  }

  public void update() {
    count--;
  }

  public boolean exploded() {
    return count == 0;
  }
}
