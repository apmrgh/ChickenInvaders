package game.engine;

import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Giant implements Animatable , Serializable {

    ArrayList<Egg> eggs = new ArrayList<>();
    ArrayList<PowerUp> powerUps = new ArrayList<>();
    public int pointx;
    public int pointy;
    public int width = 300;
    public int height = 389;
    private double vy;
    public boolean isAlive;
    public static double heart;



    public Giant(int pointx, int pointy, double vy) {

        this.pointx = pointx;
        this.pointy = pointy;
        this.vy = vy;
        isAlive = true;
        heart = 100 * Rocket.chickensLevels; //TODO قدرتش باید اصلاح بشه

    }



    public void addPowerupWhenGiantDie() throws IOException {
        if (isAlive) {
            Random random = new Random();
            int b = this.getPointx();
            int d = this.getPointy() + height / 2;
            int n1 = random.nextInt(100);
            for (int i = 0; i < 5; i++) {
                addPowerUp(b + 60 * i, d, 6, //TODO مکانشون بهتره رندوم باشه
                        random.nextInt(3)+1);
            }
        }
    }

    public void bombCrashGiant() throws IOException {
        if (heart > 0){
            heart = heart - 50;
            if (heart <= 0){
                addPowerupWhenGiantDie();
                this.isAlive = false;
            }
        }
    }

    public void addEgg(int x, int y, double vx, double vy) throws IOException {
        synchronized (eggs) {
            eggs.add(new Egg(x,y,vx,vy));
        }
    }

    public void addPowerUp(int x, int y, int vy, int type) throws IOException {
        synchronized (powerUps){
            powerUps.add(new PowerUp(x,y,vy,type));
        }
    }

    @Override
    public void paint(Graphics2D g2) {
        if (isAlive) {
            g2.drawImage(BufferedFiles.Giant_Image, pointx, pointy, width, height, null);
        }

        synchronized (eggs) {
            for (Egg egg : eggs) {
                egg.paint(g2);
            }
        }

        synchronized (powerUps) {
            for (PowerUp powerUp : powerUps){
                powerUp.paint(g2);
            }
        }
    }

    @Override
    public void move() {
        if (isAlive) {
            if (pointy < 50) {
                pointy += vy;
            }
        }

        for (Egg egg : eggs) {
            egg.move();
        }
        for (PowerUp powerUp : powerUps) {
            powerUp.move();
        }
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




































//    public void giantHitTir(Object object) throws IOException {
//        if (isAlive) {
//            if (object instanceof Tir && ((Tir) object).isalive) {
//                int a = (int) ((Tir) object).getPointx() + width / 2;
//                int c = (int) ((Tir) object).getPointy() + height / 2;
//                int b = this.getPointx() + width / 2;
//                int d = this.getPointy() + height / 2;
//                int sum = ((Tir) object).width / 2;
//                int sum1 = ((Tir) object).height / 2;
//                int sum2 = this.width / 2;
//                int sum3 = this.height / 2;
//                int sum4 = sum + sum2;
//                int sum5 = sum1 + sum3;
//                double f = Math.sqrt(sum4 * sum4 + sum5 * sum5);
//                int distance = (int) Math.sqrt((a - b) * (a - b) + (c - d) * (c - d));
//                if (distance <= f) {
//                    if (heart > 0){
//                        heart = heart - ((Tir) object).power;
//                        if (heart <= 0){
//                            addPowerupWhenGiantDie();
//                            this.isAlive = false;
//                        }
//                        ((Tir) object).isalive = false;
//                    }
//                }
//            }
//        }
//    }

//    public void giantHitRocket(Object object) throws IOException {
//        if (isAlive) {
//            if (object instanceof Rocket && ((Rocket) object).isalive ) {
//                int a = ((Rocket) object).getPointx() + width / 2;
//                int c = ((Rocket) object).getPointy() + height / 2;
//                int b = pointx + width / 2;
//                int d = pointy + height / 2;
//                int sum = ((Rocket) object).width / 2;
//                int sum1 = ((Rocket) object).height / 2;
//                int sum2 = this.width / 2;
//                int sum3 = this.height / 2;
//                int sum4 = sum + sum2;
//                int sum5 = sum1 + sum3;
//                double f = Math.sqrt(sum4 * sum4 + sum5 * sum5) - 40;
//                int distance = (int) Math.sqrt((a - b) * (a - b) + (c - d) * (c - d));
//                if (distance <= f) {
//                    if (heart > 0){
//                        heart = heart - 50;
//                        if (heart <= 0){
//                            addPowerupWhenGiantDie();
//                            this.isAlive = false;
//                        }
//                        ((Rocket) object).isalive = false;
//                        ((Rocket) object).rocketHit();
//                    }
//                }
//            }
//        }
//    }