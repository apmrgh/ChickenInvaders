package xo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameButton extends JButton /*implements ActionListener*/ {
    private MainPanel game;

//    @Override
//    public   void actionPerformed(ActionEvent e) {
//    }


    public GameButton(MainPanel game) {
        this.game = game;
//        addActionListener(this);


//        addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });


//        addActionListener((e -> {
//            if (this.getText().equals("") && !game.isFinished()) {
//                this.setText(game.getAndNextTurn());
//                if (game.isFinished()) {
//                    JOptionPane.showMessageDialog(game, game.getWinner() + " wins!");
//                }
//            }
//        }));


    }

}
