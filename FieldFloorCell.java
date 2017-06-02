import java.awt.*;

public class FieldFloorCell extends FieldCell {
  public FieldFloorCell(int x, int y) {
    super(x, y);
    color = Color.white;
  }

  public boolean canMove(int x, int y) {
    return player == null;
  }
}

