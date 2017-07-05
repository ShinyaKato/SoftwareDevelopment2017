import javax.swing.*;
import java.awt.*;

public abstract class Sprite {
  public static final int WIDTH = 21;
  public static final int HEIGHT = 15;
  public static final int CELL_WIDTH = 32;
  public static final int CELL_HEIGHT = 32;
  public static final int SCREEN_WIDTH = CELL_WIDTH * WIDTH;
  public static final int SCREEN_HEIGHT = CELL_HEIGHT * HEIGHT;
  public static final int DIFF[] = { 0, 1, 0, -1, 0 };

  protected int x, y;
  protected Color color;

  public Sprite(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void paint(Graphics g) {
    g.setColor(color);
    g.fillRect(x * CELL_WIDTH, y * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
  }
}
