import javax.swing.*;
import java.awt.*;

public class FieldWallCell extends FieldCell {
  Image image;

  public FieldWallCell(int x, int y) {
    super(x, y);
    try {
      this.image = new ImageIcon("./wall.png").getImage();
    } catch(Exception e) {}
  }

  public void update() {
  }

  public boolean canMove(int x, int y) {
    return false;
  }

  public boolean whichType(int x, int y){
    return false;
  }

  public void paint(Graphics g) {
    g.drawImage(image, x * CELL_WIDTH, y * CELL_HEIGHT, null);
  }
}

