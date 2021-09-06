package game.swing;

import game.PaintLoop;
import game.engine.BufferedFiles;
import game.engine.Client;
import game.engine.NetworkHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class HostPanel extends JPanel implements ActionListener {

    MainFrame mainFrame;
    JButton hostGame = new JButton("Host Game");
    JButton joinGame = new JButton("Join Game");
    public String playerPanel;

    public HostPanel(MainFrame mainFrame, String playerPanel) {
        this.playerPanel = playerPanel;
        this.mainFrame = mainFrame;
        mainFrame.add(this);
        setLocation(0,0);
        setSize(1000,800);
        setLayout(null);

        add(hostGame);
        hostGame.setSize(220,60);
        hostGame.setLocation(390,200);

        add(joinGame);
        joinGame.setSize(220,60);
        joinGame.setLocation(390,300);

        hostGame.addActionListener(this);
        joinGame.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(hostGame)){
            ServerPanel serverPanel = new ServerPanel(mainFrame);
            mainFrame.remove(this);
            mainFrame.add(serverPanel);
            mainFrame.repaint();
        }else if (e.getSource().equals(joinGame)){
            ClientPanel clientPanel = new ClientPanel(mainFrame, playerPanel);
            mainFrame.remove(this);
            mainFrame.add(clientPanel);
            mainFrame.repaint();
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(BufferedFiles.panels_Image, 0, 0, 1000,800,null);
    }
}
