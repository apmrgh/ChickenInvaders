package game.engine;

import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Chicken implements Animatable, Serializable {

    public ArrayList<Egg> eggs = new ArrayList<>();
    public ArrayList<Coin> coins = new ArrayList<>();
    public ArrayList<PowerUp> powerUps = new ArrayList<>();
    public double pointx;
    public double pointy;
    public double vx;
    public double vy;
    public double raduce;
    public double alfa;
    public final int width = 70;
    public final int height = 70;
    public boolean isAlive;
    public int level;
    public double heart;
    public int tag;


    public int getTag() {
        if (isAlive) {
            return tag;
        }
        return 20;
    }

    public boolean hasTag() {
        return getTag() >= 0 && getTag() < 10;
    }

    public Chicken(double pointx, double pointy, double vx, double vy,
                   double raduce, double alfa, int level) throws IOException {
        this.pointx = pointx;
        this.pointy = pointy;
        this.vx = vx;
        this.vy = vy;
        this.raduce = raduce;
        this.alfa = alfa;
        this.level = level;
        isAlive = true;

        if (level == 1) {
            heart = 2;
        } else if (level == 2) {
            heart = 3;
        } else if (level == 3) {
            heart = 5;
        } else if (level == 4) {
            heart = 8;
        }
    }


    public void addCoinAndPowerupWhenChickenDie() throws IOException {
        if (isAlive) {
            Random random = new Random();
            int b = this.getPointx() + width / 2;
            int d = this.getPointy() + height / 2;
            int n1 = random.nextInt(100);
            if (n1 < 50) {
                addCoin(b, d, 6);
//                addPowerUp(b, d, 6, 1);

//                TODO احتمالات باید درست بشه
            }
            int n2 = random.nextInt(100);
            if (n2 < 100) {
                int wp = random.nextInt(1);
                if (wp <= 1) {
                    int wpt1 = random.nextInt(1);
                    if (wpt1 < 1) {
//                        addPowerUp(b, d, 6, 2);
                    } else {
//                        addPowerUp(b, d, 6, 1);
                    }
                } else {
//                    addPowerUp(b, d, 6, 3);
                }
            }
        }
    }


    public void addEgg(int x, int y, int vy) {
        synchronized (eggs) {
            eggs.add(new Egg(x, y, 0, vy));
        }
    }


    public void addCoin(int x, int y, int vy) {
        synchronized (coins) {
            coins.add(new Coin(x, y, vy));
        }
//            coin = new Coin(x,y,vy);

    }


    public void addPowerUp(int x, int y, int vy, int type) throws IOException {
        synchronized (powerUps) {
            powerUps.add(new PowerUp(x, y, vy, type));
        }
//            powerUp = new PowerUp(x,y,vy,type);
//        }
    }


    @Override
    public void paint(Graphics2D g2) {
        if (isAlive) {
            if (level == 1) {
                g2.drawImage(BufferedFiles.ChickenType1_Image, (int) pointx,
                        (int) pointy, width, height, null);
            } else if (level == 2) {
                g2.drawImage(BufferedFiles.ChickenType2_Image, (int) pointx,
                        (int) pointy, width, height, null);
            } else if (level == 3) {
                g2.drawImage(BufferedFiles.ChickenType3_Image, (int) pointx,
                        (int) pointy, width, height, null);
            } else if (level == 4) {
                g2.drawImage(BufferedFiles.ChickenType4_Image, (int) pointx,
                        (int) pointy, width, height, null);
            }
//            g2.drawImage(bufferedImage, (int) pointx,
//                    (int) pointy, width, height, null);
        }

        synchronized (eggs) {
            for (Egg egg : eggs) {
                if (egg.isAlive) {
                    egg.paint(g2);
                }
            }
        }

        synchronized (coins) {
            for (Coin coin : coins) {
                if (coin.isAlive) {
                    coin.paint(g2);
                }
            }
        }

        synchronized (powerUps) {
            for (PowerUp powerUp : powerUps) {
                if (powerUp.isAlive) {
                    powerUp.paint(g2);
                }
            }
        }
    }


    @Override
    public void move () {


        synchronized (eggs) {
            for (Egg egg : eggs) {
                egg.move();
            }
        }
        synchronized (coins) {
            for (Coin coin : coins) {
                coin.move();
            }
        }
        synchronized (powerUps) {
            for (PowerUp powerUp : powerUps) {
                powerUp.move();
            }
        }

//        if (isAlive){
//            pointx += vx;
//            pointy += vy;
//        }
    }



    public int getPointx () {
        return (int) pointx;
    }

    public void setPointx ( int pointx){
        this.pointx = pointx;
    }

    public int getPointy () {
        return (int) pointy;
    }

    public void setPointy ( int pointy){
        this.pointy = pointy;
    }

    public double getVx () {
        return vx;
    }

    public void setVx ( double vx){
        this.vx = vx;
    }

    public double getVy () {
        return vy;
    }

    public void setVy ( double vy){
        this.vy = vy;
    }

}




























//    public void chickenHitTir(Object object) throws IOException {
//        Random random = new Random();
//        if (isAlive) {
//            if (object instanceof Tir && ((Tir) object).isalive) {
//                int a = (int) ((Tir) object).getPointx() + width / 2;
//                int c = (int) ((Tir) object).getPointy() + height / 2;
//                int b = this.getPointx() + width / 2 + 25;
//                int d = this.getPointy() + height / 2;
//                int sum = ((Tir) object).width / 2;
//                int sum1 = ((Tir) object).height / 2;
//                int sum2 = this.width / 2;
//                int sum3 = this.height / 2;
//                int sum4 = sum + sum2;
//                int sum5 = sum1 + sum3;
////                double f = Math.sqrt(sum4 * sum4 + sum5 * sum5);
//                double f = width/2 + 12;
//                int distance = (int) Math.sqrt((a - b) * (a - b) + (c - d) * (c - d));
//                if (distance <= f) {
//                    if (heart > 0){
//                        heart = heart - ((Tir) object).power;
//                        if (heart <= 0) {
//                            addCoinAndPowerupWhenChickenDie();
//                            this.isAlive = false;
//                        }
//                        ((Tir) object).isalive = false;
//                    }
//                }
//            }
//        }
//    }


//    public void chickenHitRocket(Object object){
//        if (isAlive) {
//            if (object instanceof Rocket && ((Rocket) object).isalive) {
//                int a = ((Rocket) object).getPointx() + width / 2 - 38;
//                int c = ((Rocket) object).getPointy() + height / 2 - 35;
//                int b = this.getPointx() + width / 2 ;
//                int d = this.getPointy() + height / 2;
//                int sum = ((Rocket) object).width / 2;
//                int sum1 = ((Rocket) object).height / 2;
//                int sum2 = this.width / 2;
//                int sum3 = this.height / 2;
//                int sum4 = sum + sum2;
//                int sum5 = sum1 + sum3;
//                double f = Math.sqrt((sum4 * sum4 + sum5 * sum5)) - 22;
//                int distance = (int) Math.sqrt((a - b) * (a - b) + (c - d) * (c - d));
//                if (distance <= f && ((Rocket) object).isalive) {
//                    ((Rocket) object).isalive = false;
//                    ((Rocket) object).rocketHit();
//                    isAlive = false;
//                }
//            }
//        }
//    }