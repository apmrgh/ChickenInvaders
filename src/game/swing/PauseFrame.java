package game.swing;

import game.PaintLoop;
import sun.tools.jps.Jps;

import javax.swing.*;
import java.awt.*;

public class PauseFrame extends JFrame {




    class InnerPanel extends JPanel {
        public InnerPanel() {
            setSize(600,200);
            setLocation(390,370);
            setLayout(null);
            setForeground(Color.lightGray);


            setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));

            JButton continueButton = new JButton("Continue");
            JButton exitButton = new JButton("Exit");

//            continueButton.setSize(50,60);
//            continueButton.setLocation(610, 400);
//            continueButton.setBackground(Color.black);
            continueButton.setForeground(Color.BLACK);

//            exitButton.setSize(180,60);
//            exitButton.setLocation(410, 400);
//            continueButton.setBackground(Color.black);
            exitButton.setForeground(Color.BLACK);

            add(exitButton);
            add(continueButton);

            continueButton.addActionListener(e -> {
                PaintLoop.gamePaused = true;
                dispose();
            });

            exitButton.addActionListener(e -> {
                System.exit(0);
                // TODO باید برگرده به منوی اصلی

            });
        }
    }

    InnerPanel innerPanel = new InnerPanel();


    public PauseFrame() {
        setSize(600,200);
        setLocation(390,370);
        add(innerPanel);
        setVisible(true);
    }
}
