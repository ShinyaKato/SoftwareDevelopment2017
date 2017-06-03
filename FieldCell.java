public abstract class FieldCell extends Sprite {
  protected Player player;
  protected Block block;
  protected Bomb bomb;
  protected Fire fire;
  protected Item item;

  public static boolean isin(int x, int y) {
    return 0 <= x && x < WIDTH && 0 <= y && y < HEIGHT;
  }

  public FieldCell(int x, int y) {
    super(x, y);
  }

  public abstract void update();

  public abstract boolean canMove(int x, int y);

  public void remove(Player player) {
    if(this.player == player) {
      this.player = null;
    }
  }

  public void set(Player player) {
    this.player = player;
  }

  public Player getPlayer() {
    return this.player;
  }

  public void remove(Block block) {
    if(this.block == block) {
      this.block = null;
    }
  }

  public void set(Block block) {
    this.block = block;
  }

  public Block getBlock() {
    return this.block;
  }

  public void remove(Bomb bomb) {
    if(this.bomb == bomb) {
      this.bomb = null;
    }
  }

  public void set(Bomb bomb) {
    this.bomb = bomb;
  }

  public Bomb getBomb() {
    return this.bomb;
  }

  public void remove(Fire fire) {
    if(this.fire == fire) {
      this.fire = null;
    }
  }

  public void set(Fire fire) {
    this.fire = fire;
  }

  public Fire getFire() {
    return this.fire;
  }

  public void remove(Item item) {
    if(this.item == item) {
      this.item = null;
    }
  }

  public void set(Item item) {
    this.item = item;
  }

  public Item getItem() {
    return this.item;
  }
}
