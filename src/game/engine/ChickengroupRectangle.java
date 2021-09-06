package game.engine;

import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

public class ChickengroupRectangle extends Chickengroup implements Serializable {

    private int pointx;
    private int pointy;
    private double vx = 3;



    public ChickengroupRectangle(int pointx, int pointy)
            throws IOException {
        this.pointx = pointx;
        this.pointy = pointy;
        numberOfChicken = 21;
        rectangleChickenAdd();
    }

    public void rectangleChickenAdd() throws IOException {
        Random random = new Random();
        for (int i = 0; i <3 ; i++) {
            int n = 70*i;
            for (int j = 0; j <7 ; j++) {
                int m = 70*j;
                chickens.add(new Chicken(m+pointx, n+pointy,
                        0,0,0,0,
                        random.nextInt(Rocket.chickensLevels)+1));
            }
        }
    }

    public void drawChickens(Graphics2D g2){
        for (Chicken chicken :chickens) {
            chicken.paint(g2);
        }
    }

    public void move(){
        if (pointx > 500 || pointx < 10){
            changeSpeed();
        }
        pointx += vx;
        int n = chickens.size();
        for (int j = 0; j <n ; j++) {
            chickens.get(j).setPointx((int) (chickens.get(j).getPointx()+vx));
        }

        for (Chicken chicken : chickens) {
            chicken.move();

        }
    }


    public void changeSpeed(){
        this.vx = -vx;
    }

}




//    ArrayList<Chicken> chickens = new ArrayList<>();
//    Rocket rocket;
//    int pointx;
//    int pointy;
//    double vx;
//    double vy;
//    int numberOfChicken = 21;
//    int step;
//
//
//    public void rectangleChickenAdd(){
//        for (int i = 0; i <3 ; i++) {
//            int n = 70*i;
//            for (int j = 0; j <7 ; j++) {
//                int m = 70*j;
//                chickens.add(new Chicken(m+pointx, n+pointy));
//            }
//        }
//    }
//
//    public ChickengroupRectangle(int pointx, int pointy,double vx, double vy,
//                                 Rocket rocket) {
//        this.rocket = rocket;
//        this.pointx = pointx;
//        this.pointy = pointy;
//        this.vx = vx;
//        this.vy = vy;
//        rectangleChickenAdd();
//    }
//
//    public void groupChickenHitTir(){
//        for (Chicken chicken :chickens) {
//            if (chicken.isAlive) {
//                for (Tir tir : rocket.getTirs()) {
//                    chicken.chickenHitTir(tir);
//                    if (!chicken.isAlive) {
//                        System.out.println("salam");
//                        rocket.score++;
//                    }
//                }
//            }
//        }
//    }
//
//    public void groupChickenHitRocket(){
//        for (Chicken chicken :chickens) {
//            chicken.chickenHitRocket(rocket);
//        }
//    }
//
//    public void addEggToGroupChicken(){
//        Random random = new Random();
//        new Thread(() -> {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            for (Chicken chicken : chickens) {
//                if (chicken.isAlive) {
//                    int m = random.nextInt(600);
//                    if (m == 8) {
//                        int x = chicken.getPointx() + chicken.width / 2;
//                        int y = chicken.getPointy() + chicken.height / 2;
//                        chicken.addEgg(x, y, 6);
//                    }
//                }
//            }
//        }).start();
//    }
//
//    public void RocketHitToChickenEggsAndCoins(){
//        for (Chicken chicken : chickens){
//            for (Egg egg : chicken.eggs){
//                egg.eggHitRocket(rocket);
//            }
//            for (Coin coin : chicken.coins) {
//                coin.coinHitRocket(rocket);
//            }
//        }
//    }
//
//    public void chickenClear(){
//        for (Chicken chicken : chickens){
//            chicken.isAlive = false;
//        }
//    }
//
//    public void move(){
//        if (pointx > 500 || pointx < 10){
//            changeSpeed();
//        }
//        pointx += vx;
//        int n = chickens.size();
//        for (int j = 0; j <n ; j++) {
//            chickens.get(j).setPointx((int) (chickens.get(j).getPointx()+vx));
//        }
//        for (Chicken chicken : chickens) {
//            for (Egg egg : chicken.eggs) {
//                egg.move();
//            }
//            for (Coin coin : chicken.coins) {
//                coin.move();
//            }
//        }
//    }
//
//    public void drawChickens(Graphics2D g2){
//        for (Chicken chicken :chickens) {
//            chicken.paint(g2);
//        }
//    }
//
//    public void changeSpeed(){
//        this.vx = -vx;
//        this.vy = -vy;
//    }
//
//    public void updateChickengroupRectangle(){
//        groupChickenHitTir();
//        groupChickenHitRocket();
//        addEggToGroupChicken();
//        RocketHitToChickenEggsAndCoins();
//    }


