import java.io.*;
import java.awt.event.*;

public class KeyInput {
  private BufferedReader in;
  private PrintWriter out;
  protected int[] state = new int[256];
  protected int[] buffer = new int[256];

  public KeyInput(BufferedReader in, PrintWriter out) {
    this.in = in;
    this.out = out;
  }

  public void clear() {
    for(int i = 0; i < 256; i++) {
      buffer[i] = 0;
    }
  }

  public void press(int key) {
    buffer[key] = 1;
  }

  public void send() {
    for(int i = 0; i < 256; i++) {
      state[i] = buffer[i];
      out.print(buffer[i] > 0 ? '1' : '0');
    }
    out.println("");
  }

  public void recieve() {
    try {
      String str = in.readLine();
      for(int i = 0; i < 256; i++) {
        state[i] = str.charAt(i) - '0';
      }
    } catch(IOException e) {}
  }

  public int moveDirection() {
    int dir = -1;
    if(state[KeyEvent.VK_DOWN] > 0) {
      dir = 0;
    }
    if(state[KeyEvent.VK_RIGHT] > 0) {
      dir = 1;
    }
    if(state[KeyEvent.VK_UP] > 0) {
      dir = 2;
    }
    if(state[KeyEvent.VK_LEFT] > 0) {
      dir = 3;
    }
    return dir;
  }

  public boolean bombDirection() {
    return state[KeyEvent.VK_SPACE] > 0;
  }
}
