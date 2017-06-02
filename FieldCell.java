public abstract class FieldCell extends Sprite {
  public static boolean isin(int x, int y) {
    return 0 <= x && x < WIDTH && 0 <= y && y < HEIGHT;
  }

  public FieldCell(int x, int y) {
    super(x, y);
  }

  public abstract boolean canMove(int x, int y);

  public abstract void moveTo(int x, int y, Player player);
}
