package game.swing;

import game.PaintLoop;
import game.engine.BufferedFiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class ServerPanel extends JPanel implements ActionListener {
    MainFrame mainFrame;
    JTextField numberOgPlayers = new JTextField("Enter Number Of Players");
    JTextField numberOfLevels = new JTextField("Enter Number Of Levels");
    JTextField favoriteServerPort = new JTextField("Enter Fvorite Port");
    JButton runServer = new JButton("Run Server");


    public ServerPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        mainFrame.add(this);
        setLocation(0,0);
        setSize(1000,800);
        setLayout(null);

        add(numberOgPlayers);
        numberOgPlayers.setSize(220,60);
        numberOgPlayers.setLocation(390,100);
        numberOgPlayers.setBackground(Color.green);

        add(numberOfLevels);
        numberOfLevels.setSize(220,60);
        numberOfLevels.setLocation(390,200);
        numberOfLevels.setBackground(Color.green);

        add(favoriteServerPort);
        favoriteServerPort.setSize(220,60);
        favoriteServerPort.setLocation(390,300);
        favoriteServerPort.setBackground(Color.green);


        add(runServer);
        runServer.setSize(220,60);
        runServer.setLocation(390,500);

        runServer.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        int numOgPlayers = Integer.parseInt(numberOgPlayers.getText());
//        int numOfLevels = Integer.parseInt(numberOfLevels.getText());
        int portserver = Integer.parseInt(favoriteServerPort.getText());

        MainPanel mainPanel = null;
        try {
            int randomPort= (int) (Math.random()*10000);
            while (randomPort < 1000){
                randomPort= (int) (Math.random()*10000);
            }
            System.out.println("port :  "+portserver);
            mainPanel = new MainPanel(portserver, portserver,"127.0.0.1", " mohammad");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        mainFrame.remove(this);
        mainFrame.add(mainPanel);
        try {
            new PaintLoop(mainPanel).start();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
            //TODO بازی به صورت کلاینت سرور ران بشه
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(BufferedFiles.panels_Image, 0, 0, 1000,800,null);
    }
}
