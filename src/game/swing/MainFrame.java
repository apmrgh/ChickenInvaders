package game.swing;

import game.PaintLoop;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainFrame extends JFrame {

//    PanelHandler panelHandler = new PanelHandler();
//    FirstPanel firstPanel = new FirstPanel();

    MainPanel mainPanel;


    public MainFrame() {
//        setTitle("");
        setSize(1000, 800);
        setLocationRelativeTo(null);
//        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);

        MainFrame mainFrame = this;
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
//                    mainFrame.removeAll();

//                    mainFrame.repaint();
                    PauseFrame pausePanel = new PauseFrame();
                    PaintLoop.gamePaused = false;
                }else if (e.getKeyCode() == KeyEvent.VK_S){
                    if (mainPanel != null)
                    mainPanel.server.saveToFile();
                }else if (e.getKeyCode() == KeyEvent.VK_D){
                    if (mainPanel != null)
                        mainPanel.server.saveToDB();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        setFocusable(true);
        requestFocus();


    }

    public void myAdd(MainPanel mPanel){
        this.mainPanel = mPanel;
        add(mainPanel);
    }

}
