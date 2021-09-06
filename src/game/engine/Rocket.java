package game.engine;

import game.swing.MainPanel;

import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class Rocket implements Animatable , Serializable {

//    private BufferedImage bufferedImage;
    public CopyOnWriteArraySet<Tir> tirs = new CopyOnWriteArraySet<>();
    public ArrayList<Bomb> bombs = new ArrayList<>();
    private int pointx;
    private int pointy;
    public int width = 80;
    public int height = 80;
    public boolean isalive;
    public int score;
    public int heart = 5;
    public int coin = 0;
    public int maxNumOfBombCanUse = 10;
    public int numberOfBombUsed = 0;
    public int maxTemperature = 100;
    public int temp;
    public int numOfTirPerShot;
    public int tirType;
    public int numberOfPoweupType2;
    public double firePeriod; //TODO این باید برای تیر باشد در اصل
    public int numberOfChikenKilled;
    public static boolean a = false;
    public String name;
    public static int chickensLevels;
    public static int waveNumber;





    public Rocket() {
        score = 0;
        isalive = true;
        numOfTirPerShot = 1;
        tirType = 1;
    }

    public Rocket(String name,
                  int score,
                  int bomb,
                  int coin,
                  int heart){
        this();
        this.name = name;
        this.score = score;
        maxNumOfBombCanUse = bomb;
        this.coin = coin;
        this.heart = heart;
    }

    public void addTir() throws IOException {
        if (numOfTirPerShot == 1){
            tirs.add(new Tir(pointx , pointy - height/2 , 0 , -20,tirType,numberOfPoweupType2));
        }
        else if (numOfTirPerShot == 2) {
            double degree1 = (88) * Math.PI / 180.0;
            tirs.add(new Tir(pointx+3, pointy - height/2,
                    20*Math.cos(degree1), -20*Math.sin(degree1),tirType,numberOfPoweupType2));
            double degree2 = (92) * Math.PI / 180.0;
            tirs.add(new Tir(pointx-3, pointy - height/2,
                    20*Math.cos(degree2), -20*Math.sin(degree2),tirType,numberOfPoweupType2));
        }
        else if (numOfTirPerShot == 3){
            for (int i = 0; i < 3; i++) {
                double degree = (80 + 10*i) * Math.PI / 180.0;
                tirs.add(new Tir(pointx, pointy - height/2,
                        20*Math.cos(degree), -20*Math.sin(degree),tirType,numberOfPoweupType2));
            }
        }else if (numOfTirPerShot >= 4){
                double degree1 = (81) * Math.PI / 180.0;
                tirs.add(new Tir(pointx, pointy - height/2,
                        20*Math.cos(degree1), -20*Math.sin(degree1),tirType,numberOfPoweupType2));
                double degree2 = (87) * Math.PI / 180.0;
                tirs.add(new Tir(pointx, pointy - height/2,
                        20*Math.cos(degree2), -20*Math.sin(degree2),tirType,numberOfPoweupType2));
                double degree3 = (93) * Math.PI / 180.0;
                tirs.add(new Tir(pointx, pointy - height/2,
                        20*Math.cos(degree3), -20*Math.sin(degree3),tirType,numberOfPoweupType2));
                double degree4 = (99) * Math.PI / 180.0;
                tirs.add(new Tir(pointx, pointy - height/2,
                        20*Math.cos(degree4), -20*Math.sin(degree4),tirType,numberOfPoweupType2));
        }
    }

    public void sheliktir() throws IOException {
        if (isalive) {
            if (temp <= maxTemperature) {
                synchronized (tirs) {
                    addTir();
                    temp += 5;
                    firePeriod = Math.pow(2,-tirType)*800;
                }

            } else {
                Rocket.a = false;
                temp = 160;
            }
        }
    }

    public void shelikbomb() {
        if (isalive) {
            synchronized (bombs) {
                double degree = Math.atan2(800/2 - pointy, pointx - 1000/2);
                if (numberOfBombUsed < maxNumOfBombCanUse) {
                    bombs.add(new Bomb(pointx - 35, pointy - 35,
                            -10 * Math.cos(degree),
                            10 * Math.sin(degree)));
                }
            }
            if (numberOfBombUsed < maxNumOfBombCanUse) {
                numberOfBombUsed++;
            }
        }
    }

    @Override
    public void paint(Graphics2D g2) {
        if (isalive){

            g2.drawImage(BufferedFiles.Rocket_Image,
                    pointx - width / 2,
                    pointy - height / 2, width, height, null);
            g2.setFont(new Font("TimesRoman", Font.PLAIN, 30));
            g2.setColor(Color.WHITE);
            if(name!=null)
            g2.drawString(name,pointx - width / 2,
                    pointy - height / 2 - 22);
        }

        synchronized (tirs) {
            for (Tir tir : tirs) {
                tir.paint(g2);
            }
        }

        for (Bomb bomb : bombs) {
            bomb.paint(g2);
        }
    }

    @Override
    public void move() {
        synchronized (tirs) {
            for (Tir tir : tirs) {
                tir.move();
            }
        }

        synchronized (bombs) {
            for (Bomb bomb : bombs){
                double r = Math.sqrt(Math.pow(bomb.getPointx()+bomb.width/2 - 1000 / 2, 2) +
                           Math.pow(bomb.getPointy()+bomb.height/2 - 800 / 2, 2));
                if (r < 20) {
                    bomb.isArriveToCenter = true;
                }else
                    bomb.move();
            }
        }
    }

    public boolean isBombIsArriveToCenter() {
        if (isalive) {
            boolean b = false;
            for (Bomb bomb : bombs){
                if (bomb.isArriveToCenter && bomb.isalive){
                    bomb.isalive = false;
                    b = true;
                }
            }
            return b;
        }
        return false;
    }

    public void rocketHit(){
            heart--;
            coin = 0;
    }




    public void updateRocket(){

    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getHeart() {
        return heart;
    }

    public void setHeart(int heart) {
        this.heart = heart;
    }

    public int getPointx() {
        return pointx;
    }

    public void setPointx(int pointx) {
        this.pointx = pointx;
    }

    public int getPointy() {
        return pointy;
    }

    public void setPointy(int pointy) {
        this.pointy = pointy;
    }
}















//    public ArrayList<Tir> getTirs(){
//        return tirs;
//
//    }
//
//    Thread tempReduce = new Thread(){
//        public void run(){
//            while (true) {
//                if (temp > 0 && !MainPanel.a)
//                    temp -=1;
//                try {
//                    Thread.sleep(25);
//                } catch (InterruptedException e1) {
//                    e1.printStackTrace();
//                }
//            }
//        }
//    };
//
//    Thread checkalive = new Thread(){
//        public void run(){
//            while (true) {
//                if (!isalive)
//                    new Thread(() -> {
//                        try {
//                            Thread.sleep(2000);
//                        } catch (InterruptedException e) {
