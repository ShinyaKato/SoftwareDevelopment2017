import java.awt.*;

public class FieldFloorCell extends FieldCell {
  public FieldFloorCell(int x, int y) {
    super(x, y);
    this.color = Color.white;
  }

  public void update() {
    if(block != null) block.update();
    if(bomb != null) bomb.update();
    if(item != null) item.update();
  }

  public boolean canMove(int x, int y) {
    return player == null && block == null && bomb == null;
  }

  public void paint(Graphics g) {
    g.setColor(color);
    g.fillRect(x * CELL_WIDTH, y * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
    if(block != null) block.paint(g);
    if(item != null) item.paint(g);
    if(bomb != null) bomb.paint(g);
  }
}
