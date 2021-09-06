package game.engine;

import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

public class ChickengroupCenter extends Chickengroup implements Serializable {

    private int centerx;
    private int centery;
    private Random random = new Random();


    public ChickengroupCenter(int pointx, int pointy) throws IOException {
        centerx = pointx;
        centery = pointy;
        numberOfChicken = 20;
        centerChickenAdd();
    }

    public void centerChickenAdd() throws IOException {
        int raduce;
        double alfa;
        raduce = 600;
        for (int i = 0; i < 8; i++) {
            alfa = (90 - i * 45) * Math.PI / 180.0;
            chickens.add(new Chicken((raduce * Math.cos(alfa)) + centerx - 100,
                    (raduce * Math.sin(alfa)) + centery - 100,
                    0,0,raduce,alfa,random.nextInt(Rocket.chickensLevels)+1));
        }
        raduce = 600 + 300;
        for (int i = 0; i < 12; i++) {
            alfa = (90 - i * 30) * Math.PI / 180.0;
            chickens.add(new Chicken((raduce * Math.cos(alfa)) + centerx - 100,
                    (raduce * Math.sin(alfa)) + centery - 100,
                    0,0,raduce,alfa,random.nextInt(Rocket.chickensLevels)+1));
        }
    }

    @Override
    public void drawChickens(Graphics2D g2) {
        for (Chicken chicken : chickens) {
            chicken.pointx = (chicken.raduce * Math.cos(chicken.alfa)) + centerx -35;
            chicken.pointy = (chicken.raduce * Math.sin(chicken.alfa)) + centery -100;
            chicken.paint(g2);
        }
    }



    public void move(){
        double alfa = (30 / 10.0) * Math.PI / 180.0;
        for (Chicken chicken : chickens) {
            chicken.move();
            if (chickens.indexOf(chicken) < 8){
                if (chicken.raduce >= 250) {
                    chicken.raduce -= 2;
                }
            }else {
                if (chicken.raduce >= 250 + 100) {
                    chicken.raduce -= 2;
                }
            }

            chicken.alfa = chicken.alfa - alfa;
        }
    }

}
