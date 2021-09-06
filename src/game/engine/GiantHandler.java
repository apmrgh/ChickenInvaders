package game.engine;

import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

public class GiantHandler extends Chickengroup implements Serializable {

    public Giant giant;
    public boolean canFire;
    private double degree2;




    public void giantAdd() throws IOException {
                giant = new Giant(350, -200,3);
    }

    public GiantHandler() throws IOException {
        giantAdd();
        degree2 = 45;
        new Thread(){
            public void run(){
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    degree2 += 22;
                    canFire = true;
                }
            }
        }.start();
    }




    public void fireGiantEgg(){
        Random random = new Random();
            if (giant.isAlive) {
                if (canFire) {
                    if (random.nextInt(4) == 2)
                        for (int i = 0; i < 8; i++) {
                            double degree = (degree2 * i) * Math.PI / 180.0;
                            try {
                                giant.addEgg(giant.pointx + giant.width / 2,
                                        giant.getPointy() + giant.height / 2,
                                        10 * Math.cos(degree), -10 * Math.sin(degree));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    canFire = false;
                }
            }
    }

    public void move(){
            giant.move();
    }


    public void drawGiants(Graphics2D g2){
            giant.paint(g2);
    }

    public void updateGiant() throws IOException {
        fireGiantEgg();
    }

    public boolean isOverGiant(){
        boolean a = true;

            if (giant.isAlive){
                a = false;
            }

        return a;
    }

}






















//    public void RocketHitToGiantEggsAndPowerups(){
//            for (Egg egg : giant.eggs){
//                egg.eggHitRocket(rocket);
//            }
//            for (PowerUp powerUp : giant.powerUps) {
//                powerUp.powerUpHitRocket(rocket);
//            }
//    }





//    public void giantHitTir() throws IOException {
//            if (giant.isAlive) {
//                for (Tir tir : rocket.getTirs()) {
//                    if (giant.isAlive) {
//                        giant.giantHitTir(tir);
////                        if (!giant.isAlive) {
////                            rocket.score++;
////                        }
//                    }
//                }
//            }
//    }

//    public void giantHitRocket() throws IOException{
//            giant.giantHitRocket(rocket);
//    }

//    Thread firetime = new Thread(){
//        public void run(){
//            while (true) {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e1) {
//                    e1.printStackTrace();
//                }
//                canFire = true;
//            }
//        }
//    };