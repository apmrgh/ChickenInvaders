//package game.engine;
//
//import game.swing.MainPanel;
//
//import javax.imageio.ImageIO;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class Game implements Animatable {
//
//    private BufferedImage backpic;
//    private BufferedImage bombpic;
//    private BufferedImage coinpic;
//    private BufferedImage healthpic;
//    public Rocket rocket;
//
//    Stephandler  stephandler;
//    public Long lastWaveComplete = null;
//
//    //    private int width;
////    private int height;
//    public JLabel jLabelbomb;
//    public JLabel jLabelcoin;
//    public JLabel jLabelhealth;
//    public JLabel jLabelscore;
//
//
//    public Game() throws IOException {
////        this.width = width;
////        this.height = height;
//        backpic = ImageIO.read(new File("resources/game_back.jpg"));
//        bombpic = ImageIO.read(new File("resources/rocket6.png"));
//        coinpic = ImageIO.read(new File("resources/coin.png"));
//        healthpic = ImageIO.read(new File("resources/heart.png"));
////        rocket = new Rocket(1000 / 2 , 800 - 235,0);
//        rocket = new Rocket(1000 / 2 - 1000, 800 - 1000,0);
//        stephandler = new Stephandler();
//        updategame();
//        setjlables();
//    }
//
//
//    @Override
//    public void move() {
//        rocket.move();
//        stephandler.currentGroup.move();
//    }
//
//
//
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
////                            stephandler.waveChange(rocket);
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
//
//    @Override
//    public void paint(Graphics2D g2) {
//
//        g2.drawImage(backpic, 0, 0,1000,800, null);
//
//        rocket.paint(g2);
//
//        if (stephandler.currentGroup instanceof GiantHandler){
//            ((GiantHandler) stephandler.currentGroup).drawGiants(g2);
//        }else {
//            stephandler.currentGroup.drawChickens(g2);
//        }
//
//        jLabelscore.repaint();
//        jLabelscore.setText(String.valueOf(rocket.score));
//        jLabelbomb.repaint();
//        jLabelbomb.setText(String.valueOf(rocket.maxNumOfBombCanUse
//                                        - rocket.numberOfBombUsed));
//        jLabelhealth.repaint();
//        jLabelhealth.setText(String.valueOf(rocket.heart));
//        jLabelcoin.repaint();
//        jLabelcoin.setText(String.valueOf(rocket.coin));
//
//
//        g2.drawImage(bombpic,5,700,55,55,null);
//        jLabelbomb.setBounds(60,700,55,55);
//        g2.drawImage(coinpic,115,700,55,55,null);
//        jLabelcoin.setBounds(180,700,55,55);
//        g2.drawImage(healthpic,225,700,55,55,null);
//        jLabelhealth.setBounds(280,700,55,55);
//
//    }
//
//    public void setjlables(){
//        jLabelscore = new JLabel();
//        jLabelscore.setBounds(110,0,55,55);
//        jLabelscore.setForeground(Color.WHITE);
//        jLabelscore.setFont(new Font("Serif", Font.BOLD, 35));
//        jLabelscore.setText(String.valueOf(rocket.score));
//
//        jLabelbomb = new JLabel("first jlable");
//        jLabelbomb.setForeground(Color.WHITE);
//        jLabelbomb.setFont(new Font("Serif", Font.BOLD, 35));
//        jLabelbomb.setText(String.valueOf(rocket.maxNumOfBombCanUse
//                                        - rocket.numberOfBombUsed));
//
//        jLabelcoin = new JLabel("second jlable");
//        jLabelcoin.setForeground(Color.WHITE);
//        jLabelcoin.setFont(new Font("Serif", Font.BOLD, 35));
//        jLabelcoin.setText(String.valueOf(0));
//
//        jLabelhealth = new JLabel("third jlable");
//        jLabelhealth.setForeground(Color.WHITE);
//        jLabelhealth.setFont(new Font("Serif", Font.BOLD, 35));
//        jLabelhealth.setText(String.valueOf(rocket.heart));
//    }
//
//    public Rocket getRocket() {
//        return rocket;
//    }
//
//
//}
