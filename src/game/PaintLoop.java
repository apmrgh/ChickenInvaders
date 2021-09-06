package game;

import game.engine.NetworkHandler;
import game.swing.MainPanel;

import java.io.IOException;

public class PaintLoop extends Thread {
    private MainPanel mainPanel;
    public static boolean gamePaused;

    public PaintLoop(MainPanel mainPanel) throws IOException {
        this.mainPanel = mainPanel;
        gamePaused = true;
    }

    @Override
    public void run() {
        while (true) {
            if (gamePaused) {
                try {
                    mainPanel.server.updategame();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                    mainPanel.repaint();
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
