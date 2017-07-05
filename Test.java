import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import java.io.*;

public class Test extends JPanel {
    static final int TITLE = 0;
    static final int GAME = 1;
    static final int GAMEOVER = 2;
    int mode; // 状態
    Clip sc1; // サウンドクリップ（完成時の音）
    Clip sc2; // サウンドクリップ（反転時の音）
    Clip bgm; // サウンドクリップ（BGM）

    int col[][] = new int[3][3]; // 各マスの色(0:赤，1:青)

    // コンストラクタ（初期化処理）
    public Test() {
        setPreferredSize(new Dimension(300, 300));
        addMouseListener(new OnClick());
        mode = TITLE;
        sc1 = getClip("test.wav"); // 完成時の音読み込み
        sc2 = getClip("test.wav"); // 反転時の音読み込み
        bgm = getClip("test.wav"); // BGM読み込み
    }

    // ファイルからサウンドクリップ取得
    public static Clip getClip(String filename) {
        Clip clip = null;
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(filename));
            clip = (Clip)AudioSystem.getLine(new Line.Info(Clip.class));
            clip.open(ais);
        } catch(Exception e) {
            System.out.println(e);
            System.exit(0);
        }
        return clip;
    }

    // ゲームの初期化処理
    public void startGame() {
        // 全部赤に
        int i, j;
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                col[i][j] = (int)(Math.random()*2);
            }
        }
    }
    
    // 画面描画
    public void paintComponent(Graphics g) {
        // 背景
        g.setColor(Color.black);
        g.fillRect(0, 0, 300, 300);
        // 状態ごとに画面を変える
        if (mode == TITLE) {
            g.setColor(Color.yellow);
            g.drawString("ほにゃららGAME", 100, 100);
            g.setColor(Color.white);
            g.drawString("クリックしてね！", 100, 200);
        } else if (mode == GAME) {
            int i, j;
            for (i = 0; i < 3; i++) {
                for (j = 0; j < 3; j++) {
                    if (col[i][j] == 0) {
                        g.setColor(Color.red);
                    } else {
                        g.setColor(Color.blue);
                    }
                    g.fillRect(i*100, j*100, 100, 100);
                    g.setColor(Color.black);
                    g.drawRect(i*100, j*100, 100, 100);
                }
            }
        } else if (mode == GAMEOVER) {
            g.setColor(Color.red);
            g.drawString("おめでとう！", 110, 150);
        }
    }

    // クリックされた時の処理用のクラス
    class OnClick extends MouseAdapter {
        // マウスが押された
        public void mousePressed(MouseEvent e) {
            if (mode == TITLE) {
                startGame();
                bgm.setFramePosition(0); // 巻き戻し
                bgm.loop(Clip.LOOP_CONTINUOUSLY); // 繰り返し再生
                mode = GAME;
            } else if (mode == GAME) {
                int mx = e.getX(); // マウスがクリックされた位置（X 座標）
                int my = e.getY(); // マウスがクリックされた位置（Y 座標）
                // 領域外なら何もしない（念のため）
                if (mx < 0 || mx >= 300) return;
                if (my < 0 || my >= 300) return;
                // クリックされたマスの行および列番号を算出
                int p = mx / 100; // マスの列番号
                int q = my / 100; // マスの行番号
                flip(p, q);  // そのマスを反転
                flip(p-1, q);// 左のマスを反転
                flip(p+1, q);// 右のマスを反転
                flip(p, q-1);// 上のマスを反転
                flip(p, q+1);// 下のマスを反転
                sc2.setFramePosition(0); // 巻き戻し
                sc2.start(); // 再生 
                // 全部青なら終了
                int i, j;
                boolean succ = true; // 成功か否か
                for (i = 0; i < 3; i++) {
                    for (j = 0; j < 3; j++) {
                        if (col[i][j] == 0) {
                            succ = false;
                        }
                    }
                }
                if (succ) {
                    bgm.stop(); // BGMを止める
                    sc1.setFramePosition(0); // 巻き戻し
                    sc1.start(); // 再生 
                    mode = GAMEOVER;
                }
            } else if (mode == GAMEOVER) {
                mode = TITLE;
            }
            repaint();
        }
    }

    // x列，y行のマスの色を反転
    void flip(int x, int y) {
        // 領域外なら何もしない
        if (x < 0 || x >= 3) return;
        if (y < 0 || y >= 3) return;
        // 色の反転
        if (col[x][y] == 0) {
            col[x][y] = 1;
        } else {
            col[x][y] = 0;
        }
    }

    // 起動時
    public static void main(String[] args) {
        // ウィンドウの作成と表示
        JFrame f = new JFrame();
        f.getContentPane().setLayout(new FlowLayout());
        f.getContentPane().add(new Test());
        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setVisible(true);
    }
}