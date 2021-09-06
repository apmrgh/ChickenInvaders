package game.swing;

import game.PaintLoop;
import game.engine.BufferedFiles;
import game.engine.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class ClientPanel extends JPanel implements ActionListener {

    private MainFrame mainFrame;
    private JTextField serverport = new JTextField("Enter Server Port");
    private JTextField serverip = new JTextField("Enter Server IP");
    private JButton asAPlayer = new JButton("As Player");
    private JButton asASpectator = new JButton("As Spectator");
    public String playerName;


    public ClientPanel(MainFrame mainFrame, String playerName) {
        this.playerName = playerName;
        this.mainFrame = mainFrame;
        mainFrame.add(this);
        setLocation(0,0);
        setSize(1000,800);
        setLayout(null);

        add(asAPlayer);
        asAPlayer.setSize(220,60);
        asAPlayer.setLocation(85,650);

        add(serverport);
        serverport.setSize(220,60);
        serverport.setLocation(390,300);

        add(serverip);
        serverip.setSize(220,60);
        serverip.setLocation(390,400);

        add(asASpectator);
        asASpectator.setSize(220,60);
        asASpectator.setLocation(695,650);

        asAPlayer.addActionListener(this);
        asASpectator.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int serverPort = Integer.parseInt(serverport.getText());
        String serverIp = serverip.getText();

        if (e.getSource().equals(asAPlayer)){
            int sp = 3458;
            MainPanel mainPanel = null;
            try {
                Client.hasRocket = true;
                mainPanel = new MainPanel(sp,serverPort,serverIp, playerName);
                mainFrame.remove(this);
                mainFrame.add(mainPanel);
                new PaintLoop(mainPanel).start();
            } catch (IOException e1) {
                e1.printStackTrace();
            }


        }else if (e.getSource().equals(asASpectator)){
            int sp = 3458;
            MainPanel mainPanel = null;
            try {
                Client.hasRocket = false;
                mainPanel = new MainPanel(sp,serverPort,serverIp, playerName);
                mainFrame.remove(this);
                mainFrame.add(mainPanel);
                new PaintLoop(mainPanel).start();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(BufferedFiles.panels_Image, 0, 0, 1000,800,null);
    }
}
