import javax.swing.*;
import java.awt.*;

public class Player extends Sprite {
  public Player(int x, int y) {
    super(x, y);
    this.color = Color.yellow;
  }

  public Point currentPosition() {
    return new Point(x, y);
  }

  public Point nextPosition(int dir) {
    return new Point(x + DIFF[dir], y + DIFF[dir + 1]);
  }

  public void moveTo(int x, int y) {
    this.x = x;
    this.y = y;
  }
}
