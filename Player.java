import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;

public class Player extends Sprite {
  BufferedImage image;

  public Player(int x, int y) {
    super(x, y);
    try {
      this.image = ImageIO.read(new File("./monster.gif"));
    } catch(Exception e) {}
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

  public void paint(Graphics g) {
    g.drawImage(image, x * CELL_WIDTH, y * CELL_HEIGHT, null);
  }
}
