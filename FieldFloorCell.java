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

    public boolean whichType(int x, int y){
	return true;
    }

  public void paint(Graphics g) {
    super.paint(g);
    if(block != null) block.paint(g);
    if(bomb != null) bomb.paint(g);
    if(fire != null) fire.paint(g);
    if(item != null) item.paint(g);
  }
}
