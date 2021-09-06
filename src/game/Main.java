package game;

import game.engine.NetworkHandler;
import game.swing.FirstPanel;
import game.swing.MainFrame;
import game.swing.MainPanel;
import game.swing.SecondPanel;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.net.URL;


public class Main {
    public static void main(String[] args) throws IOException {

//        MainFrame mainFrame = new MainFrame();
//        MainPanel mainPanel = new MainPanel();
//        mainFrame.add(mainPanel);
//        new PaintLoop(mainPanel).start();

//        int a = 10;
//        String heartView = "Giant Heart: " + a;
//        System.out.println(heartView);

new Thread(new Runnable() {
    public void run() {
        try {
            Clip clip = AudioSystem.getClip();
            File file = new File("resources/voice.wav");
            AudioInputStream inputStream =
                    AudioSystem.getAudioInputStream(file);
            clip.open(inputStream);
            clip.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}).start();


        FirstPanel firstPanel = new FirstPanel();


    }
}
