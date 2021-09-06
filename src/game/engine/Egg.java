package game.engine;

import game.engine.Animatable;
import game.engine.BufferedFiles;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class Egg implements Animatable, Serializable {

    public int pointx;
    public int pointy;
    public int width = 20;
    public int height = 28;
    private double vy;
    private double vx;
    public boolean isAlive;


    public Egg(int pointx, int pointy, double vx, double vy) {
        this.pointx = pointx;
        this.pointy = pointy;
        this.vx = vx;
        this.vy = vy;
        isAlive = true;

    }

    @Override
    public void paint(Graphics2D g2) {
        if (isAlive)
        g2.drawImage(BufferedFiles.Egg_Image,
                pointx, pointy,width,height, null);
    }

    @Override
    public void move() {
        if (isAlive) {
            pointy += vy;
            pointx += vx;
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





//    public void eggHitRocket(Object object){
//        if (isAlive) {
//            if (object instanceof Rocket && ((Rocket) object).isalive ) {
//                int a = ((Rocket) object).getPointx() + width / 2 -12;
//                int c = ((Rocket) object).getPointy() + height / 2;
//                int b = pointx + width / 2;
//                int d = pointy + height / 2;
//                int sum = ((Rocket) object).width / 2;
//                int sum1 = ((Rocket) object).height / 2;
//                int sum2 = this.width / 2;
//                int sum3 = this.height / 2;
//                int sum4 = sum + sum2;
//                int sum5 = sum1 + sum3;
//                double f = Math.sqrt(sum4 * sum4 + sum5 * sum5) - 35;
//                int distance = (int) Math.sqrt((a - b) * (a - b) + (c - d) * (c - d));
//                if (distance <= f) {
//                    ((Rocket) object).isalive = false;
//                    ((Rocket) object).rocketHit();
//                    isAlive = false;
//                }
//            }
//        }
//    }