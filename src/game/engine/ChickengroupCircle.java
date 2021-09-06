package game.engine;

import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

public class ChickengroupCircle extends Chickengroup implements Serializable {

    private int centerx;
    private int centery;
    private int targetx;
    private int targety;
    private int dx;
    private int dy;
    private double vdx;
    private double vdy;
    private Random random = new Random();



    public ChickengroupCircle(int pointx, int pointy) throws IOException {
        centerx = pointx;
        centery = pointy;
        numberOfChicken = 20;
        circleChickenAdd();
        new Thread(){
            public void run(){
                while (true) {
                    targetx = random.nextInt(600) + 200;
                    targety = random.nextInt(400) + 100;
                    dx = targetx - centerx;
                    dy = targety - centery;
                    vdx = (0.05 * dx) / 3.5;
                    vdy = (0.05 * dy) / 3.5;
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public void circleChickenAdd() throws IOException {
        int raduce;
        double alfa;
        for (int i = 0; i < 8; i++) {
            raduce = 100;
            alfa = (90 - i * 45) * Math.PI / 180.0;
            chickens.add(new Chicken((raduce * Math.cos(alfa)) + centerx - 100,
                    (raduce * Math.sin(alfa)) + centery - 100,
                    0,0,raduce,alfa,random.nextInt(Rocket.chickensLevels)+1));
        }
        for (int i = 0; i < 12; i++) {
            raduce = 180;
            alfa = (90 - i * 30) * Math.PI / 180.0;
            chickens.add(new Chicken((raduce * Math.cos(alfa)) + centerx - 100,
                    (raduce * Math.sin(alfa)) + centery - 100,
                    0,0,raduce,alfa,random.nextInt(Rocket.chickensLevels)+1));
        }
    }


    @Override
    public void drawChickens(Graphics2D g2) {
        for (Chicken chicken : chickens) {
            double dis = chicken.raduce;
            if (chicken.raduce == 180.0) {
                dis = chicken.raduce - 80;
            }
            chicken.pointx = (chicken.raduce * Math.cos(chicken.alfa))
                    + centerx - dis + 35;
            chicken.pointy = (chicken.raduce * Math.sin(chicken.alfa))
                    + centery - dis - 60;
            chicken.paint(g2);
        }
    }


    public void move(){
        double disxs = Math.abs(centerx - targetx);
        double disys = Math.abs(centery - targety);
        if (disxs > 10 && disys > 10) {
            centerx += vdx;
            centery += vdy;
        }
        double alfa = (18 / 10.0) * Math.PI / 180.0;
        for (Chicken chicken : chickens) {
            chicken.move();
            chicken.alfa = chicken.alfa - alfa;
        }

    }

}
