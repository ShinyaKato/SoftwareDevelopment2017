import javax.swing.*;
import java.awt.*;

public class FieldFloorCell extends FieldCell {
  Image image;

  public FieldFloorCell(int x, int y) {
    super(x, y);
    try {
      this.image = new ImageIcon("./floor.png").getImage();
    } catch(Exception e) {}
  }

  public void update() {
    if(block != null) block.update();
    if(bomb != null) bomb.update();
    if(item != null) item.update();
    if(fire != null) fire.update();
  }

  public boolean canMove(int x, int y) {
    return player == null && block == null && bomb == null;
  }

    public boolean whichType(int x, int y){
	return true;
    }

  public void paint(Graphics g) {
    g.drawImage(image, x * CELL_WIDTH, y * CELL_HEIGHT, null);
    if(block != null) block.paint(g);
    if(bomb != null) bomb.paint(g);
    if(fire != null) fire.paint(g);
    if(item != null) item.paint(g);
  }
}
