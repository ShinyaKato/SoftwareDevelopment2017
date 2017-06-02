import java.awt.*;

public class FieldFloorCell extends FieldCell {
  private Player player;

  public FieldFloorCell(int x, int y) {
    super(x, y);
    color = Color.white;
  }

  public boolean canMove(int x, int y) {
    return true;
  }

  public void moveTo(int x, int y, Player player) {
  }
}

