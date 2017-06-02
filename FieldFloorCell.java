import java.awt.*;

public class FieldFloorCell extends FieldCell {
  public FieldFloorCell(int x, int y, boolean blocked) {
    super(x, y);
    this.blocked = blocked;
    this.color = Color.white;
  }

  public boolean canMove(int x, int y) {
    return player == null && blocked == false;
  }

  public void paint(Graphics g) {
    if(blocked) {
      g.setColor(Color.cyan);
    } else {
      g.setColor(color);
    }
    g.fillRect(x * CELL_WIDTH, y * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
  }
}
