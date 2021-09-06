package game.swing;

import game.engine.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class MainPanel extends JPanel {

    public Server server;
    private Client client;



    public MainPanel(ArrayList<String> users, String playerName) throws IOException {
        Client.hasRocket = true;
        server = new Server(users);
        client=new Client(8152);
        client.plyerNamed(playerName);
        setLayout(null);
        setBounds(0, 0, 1000, 800);
        add(client.jLabelbomb);
        add(client.jLabelcoin);
        add(client.jLabelhealth);
        add(client.jLabelscore);
        add(client.tempBar.progressBar);
        add(client.jLabelLevelNum);
        add(client.jLabelWaveNum);
        add(client.jLabelGiantHeart);

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                client.mouseDragged(e.getX(), e.getY());
            }
            @Override
            public void mouseMoved(MouseEvent e) {
                client.mouseMoved(e.getX(), e.getY());
            }
        });

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                client.mouseClicked(e.getButton());
            }
            @Override
            public void mousePressed(MouseEvent e) {
                client.mousePressed(e.getButton());
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                client.mouseReleased(e.getButton());
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    public MainPanel(int serverPort, int clientPort,
                     String serverip, String playerName)
            throws IOException {
        server = new Server(serverPort);
        client=new Client(clientPort);
        client.sip = serverip;
//        client.plyerAdded(playerName);
        if (Client.hasRocket) {
            client.plyerNamed(playerName);
        }
        setLayout(null);
        setBounds(0, 0, 1000, 800);
        add(client.jLabelbomb);
        add(client.jLabelcoin);
        add(client.jLabelhealth);
        add(client.jLabelscore);
        add(client.tempBar.progressBar);
        add(client.jLabelLevelNum);
        add(client.jLabelWaveNum);
        add(client.jLabelGiantHeart);

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                client.mouseDragged(e.getX(), e.getY());
            }
            @Override
            public void mouseMoved(MouseEvent e) {
                client.mouseMoved(e.getX(), e.getY());
            }
        });

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                client.mouseClicked(e.getButton());
            }
            @Override
            public void mousePressed(MouseEvent e) {
                client.mousePressed(e.getButton());
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                client.mouseReleased(e.getButton());
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        client.paint((Graphics2D) g);
    }

}
