import javax.swing.*;
import java.awt.*;

public class Bomb extends Sprite {
  private Image image;
  private int count;
  private boolean used = false;
  private Player player;

  public Bomb(Player player) {
    super(0, 0);
    this.player = player;
    try {
      this.image = new ImageIcon("./bomb_anim.png").getImage();
    } catch(Exception e) {}
  }

  public void set(int x, int y) {
    this.x = x;
    this.y = y;
    this.used = true;
    this.count = 150;
  }

  public void update() {
    count--;
  }

  public boolean exploded() {
    return count <= 0;
  }

  public void expload() {
    used = false;
  }

  public boolean isUsed() {
    return used;
  }

  public Player getPlayer() {
    return player;
  }

  public void paint(Graphics g) {
    int gx = (count > 30 ? 0 : (30 - count) / 5) * CELL_WIDTH;
    if(30 < count && count <= 90) {
      gx = (1 + ((90 - count) / 15) % 2) * CELL_WIDTH;
    }
    int gy = 0;
    int px = x * CELL_WIDTH;
    int py = y * CELL_HEIGHT;
    g.drawImage(image, px, py, px + CELL_WIDTH, py + CELL_HEIGHT, gx, gy, gx + CELL_WIDTH, gy + CELL_HEIGHT, null);
  }
}
