package game.engine;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Client extends JPanel {


    private List<Rocket> rockets;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private Socket socket;

    private double vbackpic = 0;

    public Stephandler stephandler;
    public JLabel jLabelbomb;
    public JLabel jLabelcoin;
    public JLabel jLabelhealth;
    public JLabel jLabelscore;

    public JLabel jLabelWaveNum;
    public JLabel jLabelLevelNum;
    public JLabel jLabelGiantHeart;

    public TempBar tempBar = new TempBar();
    public int port;
    public String sip;
    public static boolean hasRocket;


    public Client(int port) throws IOException {
        this.port = port;
        connectToServer();
        setjlables();
        new Thread(this::updateStepHandlerAndRocket).start();

//        ArrayList request = new ArrayList();
//        request.add("plyerAdded");
//        synchronized (objectOutputStream) {
//            objectOutputStream.writeObject(request);
//            objectOutputStream.flush();
//        }

    }

    private void connectToServer() {
        try {
//            System.out.println("connecting");
            Thread.sleep(100);
            socket = new Socket(sip, port);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
//            System.out.println("connected");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {

        }

    }

    private void updateStepHandlerAndRocket() {
//        ArrayList request = new ArrayList();
//        request.add("Stephandler");
//        synchronized (objectOutputStream) {
//            objectOutputStream.writeObject(request);
//            objectOutputStream.flush();
        while (true) {
            try {
                ArrayList request = new ArrayList();
                request.add("Stephandler");
                synchronized (objectOutputStream) {
                    objectOutputStream.writeObject(request);
                    objectOutputStream.flush();
                }
                ArrayList arrayList= (ArrayList) objectInputStream.readObject();
                if(arrayList.get(0).equals("step")) {
                    stephandler = (Stephandler) objectInputStream.readObject();
                    rockets = (List<Rocket>) objectInputStream.readObject();
                    ArrayList<String> jlablevalue = new ArrayList<>();
                    jlablevalue = (ArrayList<String>) objectInputStream.readObject();
                    jLabelscore.setText(jlablevalue.get(0));
                    jLabelbomb.setText(jlablevalue.get(1));
                    jLabelcoin.setText(jlablevalue.get(2));
                    jLabelhealth.setText(jlablevalue.get(3));
                    tempBar.progressBar.setMaximum(Integer.parseInt(jlablevalue.get(4)));
                    tempBar.progressBar.setValue(Integer.parseInt(jlablevalue.get(5)));
                    jLabelLevelNum.setText("Leve: " + jlablevalue.get(6));
                    jLabelWaveNum.setText("Wave: " + jlablevalue.get(7));
                    jLabelGiantHeart.setText(jlablevalue.get(8));
                }else if(arrayList.get(0).equals("newUser")){
                    System.out.println(arrayList.get(1));
                }
            } catch (IOException | ClassNotFoundException e) {
                //TODO برگرده به منوی اصلی
                System.exit(0);
//                e.printStackTrace();
            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void paint(Graphics2D g2) {

        g2.drawImage(BufferedFiles.backpic,
                0, (int) vbackpic, 1000, 800, null);
        g2.drawImage(BufferedFiles.backpic,
                0, (int)(vbackpic-800), 1000, 800, null);
        vbackpic += 0.3;
        if (vbackpic > 800){
            vbackpic %= 800;
        }
        if (rockets != null){
            for (Rocket rocket : rockets) {
                rocket.paint(g2);
            }
        }
        if(stephandler!=null) {
            if (stephandler.currentGroup instanceof GiantHandler) {
                ((GiantHandler) stephandler.currentGroup).drawGiants(g2);
            } else {
                stephandler.currentGroup.drawChickens(g2);
            }
        }

        jLabelscore.repaint();
        jLabelbomb.repaint();
        jLabelhealth.repaint();
        jLabelcoin.repaint();
        jLabelLevelNum.repaint();
        jLabelWaveNum.repaint();
        jLabelGiantHeart.repaint();


        g2.drawImage(BufferedFiles.bombpic, 5, 700, 55, 55, null);
        jLabelbomb.setBounds(60, 700, 55, 55);
        g2.drawImage(BufferedFiles.coinpic, 115, 700, 55, 55, null);
        jLabelcoin.setBounds(180, 700, 55, 55);
        g2.drawImage(BufferedFiles.healthpic, 225, 700, 55, 55, null);
        jLabelhealth.setBounds(280, 700, 55, 55);

    }

    public void setjlables() {


        jLabelGiantHeart = new JLabel();
        jLabelGiantHeart.setBounds(800,750,150,30);
        jLabelGiantHeart.setForeground(Color.WHITE);
        jLabelGiantHeart.setFont(new Font("Serif", Font.BOLD, 20));
        jLabelGiantHeart.setText(String.valueOf(0));

        jLabelLevelNum = new JLabel();
        jLabelLevelNum.setBounds(815,5,70,30);
        jLabelLevelNum.setForeground(Color.WHITE);
        jLabelLevelNum.setFont(new Font("Serif", Font.BOLD, 20));
        jLabelLevelNum.setText(String.valueOf(0));

        jLabelWaveNum = new JLabel();
        jLabelWaveNum.setBounds(900,5,100,30);
        jLabelWaveNum.setForeground(Color.WHITE);
        jLabelWaveNum.setFont(new Font("Serif", Font.BOLD, 20));
        jLabelWaveNum.setText(String.valueOf(0));

        jLabelscore = new JLabel();
        jLabelscore.setBounds(110, 0, 55, 55);
        jLabelscore.setForeground(Color.WHITE);
        jLabelscore.setFont(new Font("Serif", Font.BOLD, 35));
        jLabelscore.setText(String.valueOf(0));

        jLabelbomb = new JLabel("first jlable");
        jLabelbomb.setForeground(Color.WHITE);
        jLabelbomb.setFont(new Font("Serif", Font.BOLD, 35));
        jLabelbomb.setText(String.valueOf(0));

        jLabelcoin = new JLabel("second jlable");
        jLabelcoin.setForeground(Color.WHITE);
        jLabelcoin.setFont(new Font("Serif", Font.BOLD, 35));
        jLabelcoin.setText(String.valueOf(0));

        jLabelhealth = new JLabel("third jlable");
        jLabelhealth.setForeground(Color.WHITE);
        jLabelhealth.setFont(new Font("Serif", Font.BOLD, 35));
        jLabelhealth.setText(String.valueOf(0));
    }

    public void mouseMoved(int x, int y) {
        ArrayList request = new ArrayList();
        request.add("mouseMoved");
        request.add(x);
        request.add(y);
        try {
            synchronized (objectOutputStream) {
                objectOutputStream.writeObject(request);
                objectOutputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mouseClicked(int keyButton) {
        ArrayList request = new ArrayList();
        request.add("mouseClicked");
        request.add(keyButton);
        try {
            synchronized (objectOutputStream) {
                objectOutputStream.writeObject(request);
                objectOutputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mousePressed(int keyButton) {
        ArrayList request = new ArrayList();
        request.add("mousePressed");
        request.add(keyButton);
        try {
            synchronized (objectOutputStream) {
                objectOutputStream.writeObject(request);
                objectOutputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mouseReleased(int keyButton) {
        ArrayList request = new ArrayList();
        request.add("mouseReleased");
        request.add(keyButton);
        try {
            synchronized (objectOutputStream) {
                objectOutputStream.writeObject(request);
                objectOutputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mouseDragged(int x, int y) {
        ArrayList request = new ArrayList();
        request.add("mouseDragged");
        request.add(x);
        request.add(y);
        try {
            synchronized (objectOutputStream) {
                objectOutputStream.writeObject(request);
                objectOutputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void plyerNamed(String plyerName) {
        ArrayList request = new ArrayList();
        request.add("plyerNamed");
        request.add(plyerName);
        try {
            synchronized (objectOutputStream) {
                objectOutputStream.writeObject(request);
                objectOutputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}





















    //    @Override
//    public void move() {
//        rocket.move();
////        stephandler.currentGroup.move();
//    }

    //    public void updategame() throws IOException {
//
//                    move();
//                    rocket.updateRocket();
//
//                    if (stephandler.waveComplete()) {
//                        if(lastWaveComplete == null) {
//                            lastWaveComplete = System.currentTimeMillis();
//                        } else if (System.currentTimeMillis()
//                                - lastWaveComplete > 4000) {
//                            stephandler.waveChange(rocket);
//                            lastWaveComplete = null;
//                        }
//                    }
//
//                    try {
//                        if (stephandler.currentGroup instanceof GiantHandler){
//                            ((GiantHandler) stephandler.currentGroup).updateGiant();
//                        }else {
//                            stephandler.currentGroup.updateChickengroup();
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    if (rocket.isBombIsArriveToCenter()){
//                        if (stephandler.currentGroup instanceof GiantHandler){
//                            ((GiantHandler) stephandler.currentGroup).giant.bombCrashGiant();
//                        }else {
//                            stephandler.currentGroup.chickenClear();
////                            System.out.println(rocket.score);
//                            rocket.score +=
//                            (stephandler.currentGroup.numberOfChicken- rocket.numberOfChikenKilled);
////                            System.out.println(stephandler.currentGroup.numberOfChicken);
////                            System.out.println(rocket.numberOfChikenKilled);
////                            System.out.println(rocket.score);
////                            System.out.println();
//                        }
//                    }
//    }


