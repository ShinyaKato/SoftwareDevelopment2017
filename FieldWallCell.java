import java.awt.*;

public class FieldWallCell extends FieldCell {
  public FieldWallCell(int x, int y) {
    super(x, y);
    color = Color.blue;
  }

  public void update() {
  }

  public boolean canMove(int x, int y) {
    return false;
  }
}

