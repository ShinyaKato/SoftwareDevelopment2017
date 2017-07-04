//負けた状態を表すために、キャラクターが爆風に当たったら濃い灰色に変色して、動けないようにする。
import java.awt.*;

public class Dead extends FieldCell {
  public Dead(int x, int y) {
    super(x, y);
    color = Color.darkGray;
  }

  public void update() {
  }

  public boolean canMove(int x, int y) {
    return false;
  }
    public boolean whichType(int x, int y){
	return false;
    }
}

