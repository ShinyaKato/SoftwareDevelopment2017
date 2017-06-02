public abstract class FieldCell extends Sprite {
  protected Player player;
  protected boolean blocked;

  public static boolean isin(int x, int y) {
    return 0 <= x && x < WIDTH && 0 <= y && y < HEIGHT;
  }

  public FieldCell(int x, int y) {
    super(x, y);
  }

  public abstract boolean canMove(int x, int y);

  public void remove(Player player) {
    if(this.player == player) {
      this.player = null;
    }
  }

  public void set(Player player) {
    this.player = player;
  }
}
