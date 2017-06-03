import javax.swing.*;
import java.awt.*;

public class Fire extends Sprite {
  public int count = 90;

  public Fire(int x, int y) {
    super(x, y);
    this.color = Color.red;
  }

  public void update() {
    count--;
  }

  public boolean exploded() {
    return count <= 0;
  }
}
