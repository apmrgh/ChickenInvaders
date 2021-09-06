package xo;

import game.engine.BufferedFiles;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(BufferedFiles.backpic,
                0, 0, 1000, 800, null);
        super.paintComponent(g);
        String name = "mohammad";
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g.setColor(Color.CYAN);
        g.drawString("mohammad <p color=\\\"#00FF00\\\">NOTICE</p>", 200,200);
    }


    //    private String currentTurn = "X";
//    private GameButton[][] buttons = new GameButton[3][3];
//
    public MainPanel() {
        setLayout(null);
        setBounds(0, 0, 1000, 800);

//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                buttons[i][j] = new GameButton(this);
//                add(buttons[i][j]);
//            }
//        }
    }
//
//    String getWinner() {
//        for (int i = 0; i < 3; i++) {
//            String s = buttons[i][0].getText();
//            boolean ok = !s.equals("");
//            for (int j = 0; j < 3; j++) {
//                if (!s.equals(buttons[i][j].getText())) {
//                    ok = false;
//                }
//            }
//            if (ok) {
//                return s;
//            }
//        }
//
//
//        for (int i = 0; i < 3; i++) {
//            String s = buttons[0][i].getText();
//            boolean ok = !s.equals("");
//            for (int j = 0; j < 3; j++) {
//                if (!s.equals(buttons[j][i].getText())) {
//                    ok = false;
//                }
//            }
//            if (ok) {
//                return s;
//            }
//        }
//        {
//            String s = buttons[0][0].getText();
//            boolean ok = !s.equals("");
//            for (int j = 0; j < 3; j++) {
//                if (!s.equals(buttons[j][j].getText())) {
//                    ok = false;
//                }
//            }
//            if (ok) {
//                return s;
//            }
//        }
//
//        {
//            String s = buttons[0][2].getText();
//            boolean ok = !s.equals("");
//            for (int j = 0; j < 3; j++) {
//                if (!s.equals(buttons[j][2 - j])) {
//                    ok = false;
//                }
//            }
//            if (ok) {
//                return s;
//            }
//        }
//        return null;
//    }
//
//    public boolean isFinished() {
//        return getWinner() != null;
//    }
//
//    public String getAndNextTurn() {
//        String nextTurn = "X";
//        if (currentTurn.equals("X")) {
//            nextTurn = "O";
//        }
//
//        String temp = currentTurn;
//        currentTurn = nextTurn;
//
//        return temp;
//    }
}
