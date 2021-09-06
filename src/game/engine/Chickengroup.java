package game.engine;

import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public abstract class Chickengroup implements Serializable {

    protected ArrayList<Chicken> chickens = new ArrayList<>();
    public int numberOfChicken;




    public Chickengroup() throws IOException {
    }

    public void addEggToGroupChicken(){
        Random random = new Random();
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (Chicken chicken : chickens) {
                if (chicken.isAlive) {
                    if (chicken.level == 1) {
                        int m = random.nextInt(800);
                        if (m == 3) {
                            int x = chicken.getPointx() + chicken.width / 2;
                            int y = chicken.getPointy() + chicken.height / 2;
                            chicken.addEgg(x, y, 6);
                        }
                    }else if (chicken.level == 2) {
                        int m = random.nextInt(800);
                        if (m == 2) {
                            int x = chicken.getPointx() + chicken.width / 2;
                            int y = chicken.getPointy() + chicken.height / 2;
                            chicken.addEgg(x, y, 6);
                        }
                    }else if (chicken.level == 3) {
                        int m = random.nextInt(400);
                        if (m == 1) {
                            int x = chicken.getPointx() + chicken.width / 2;
                            int y = chicken.getPointy() + chicken.height / 2;
                            chicken.addEgg(x, y, 12);
                        }
                    }else if (chicken.level == 4) {
                        int m = random.nextInt(200);
                        if (m == 0) {
                            int x = chicken.getPointx() + chicken.width / 2;
                            int y = chicken.getPointy() + chicken.height / 2;
                            chicken.addEgg(x, y, 12);
                        }
                    }
                }
            }
        }).start();
    }

    public void chickenClear() throws IOException {
        for (Chicken chicken : chickens){
            for (Coin coin:chicken.coins) {
                coin.isAlive = false;
            }for (PowerUp powerUp:chicken.powerUps) {
                powerUp.isAlive = false;
            }
            chicken.addCoinAndPowerupWhenChickenDie();
            chicken.isAlive = false;
        }
    }

    public void drawChickens(Graphics2D g2){
        for (Chicken chicken :chickens) {
            chicken.paint(g2);
        }
    }

    public boolean isOver(){
        boolean a = true;
        for (Chicken chicken : chickens) {
            if (chicken.isAlive){
                a = false;
            }
        }
        return a;
    }

    public void updateChickengroup() throws IOException {
        addEggToGroupChicken();
    }

        public void move(){

    }


}





















//    public void RocketHitToChickenEggsAndCoinsAndPowerups(){
//        for (Chicken chicken : chickens){
//            for (Egg egg : chicken.eggs){
//                egg.eggHitRocket(rocket);
//            }
//            for (Coin coin : chicken.coins) {
//                coin.coinHitRocket(rocket);
//            }
//            for (PowerUp powerUp : chicken.powerUps) {
//                powerUp.powerUpHitRocket(rocket);
//            }
//        }
//    }


//    public void groupChickenHitTir() throws IOException {
//        for (Chicken chicken :chickens) {
//            if (chicken.isAlive) {
//                for (Tir tir : rocket.getTirs()) {
//                    if (chicken.isAlive) {
//                        chicken.chickenHitTir(tir);
//                        if (!chicken.isAlive) {
//                            rocket.score++;
//                            rocket.numberOfChikenKilled++;
//                        }
//                    }
//                }
//            }
//        }
//    }

//    public void groupChickenHitRocket(){
//        for (Chicken chicken :chickens) {
//            chicken.chickenHitRocket(rocket);
//        }
//    }