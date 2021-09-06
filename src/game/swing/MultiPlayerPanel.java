package game.swing;

import game.Main;
import game.PaintLoop;
import game.engine.BufferedFiles;
import game.engine.Client;
import game.engine.NetworkHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MultiPlayerPanel extends JPanel implements ActionListener {


    public MainFrame mainFrame;
    public JButton singlePlayer = new JButton("Single Player");
    public JButton multiPlayer = new JButton("Multi Player");
    public String plyerName;

    public MultiPlayerPanel(MainFrame mainFrame, String playerName) {
        this.plyerName = playerName;
        this.mainFrame = mainFrame;
        mainFrame.add(this);
        setLocation(0,0);
        setSize(1000,800);
        setLayout(null);

        add(singlePlayer);
        singlePlayer.setSize(220,60);
        singlePlayer.setLocation(390,500);

        add(multiPlayer);
        multiPlayer.setSize(220,60);
        multiPlayer.setLocation(390,400);

        singlePlayer.addActionListener(this);
        multiPlayer.addActionListener(this);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(singlePlayer)){

            MainPanel mainPanel = null;
            Client.hasRocket = true;
            try {
                int randomPort= (int) (Math.random()*10000);
                while (randomPort < 1000){
                    randomPort= (int) (Math.random()*10000);
                }
                System.out.println("port :  "+randomPort);
                mainPanel = new MainPanel(randomPort, randomPort,
                        "127.0.0.1", plyerName);
                mainFrame.remove(this);
                mainFrame.myAdd(mainPanel);
                new PaintLoop(mainPanel).start();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }else if (e.getSource().equals(multiPlayer)){
            HostPanel hostPanel = new HostPanel(mainFrame, plyerName);
            mainFrame.remove(this);
            mainFrame.add(hostPanel);
            mainFrame.repaint();
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(BufferedFiles.panels_Image, 0, 0, 1000,800,null);
    }
}
