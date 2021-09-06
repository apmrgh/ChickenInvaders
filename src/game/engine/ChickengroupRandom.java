package game.engine;

import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

public class ChickengroupRandom extends Chickengroup implements Serializable {


    private int centerx;
    private int centery;
    private int[] targetxs = new int[10];
    private int[] targetys = new int[10];
    private Random random = new Random();


//    public Chicken tagToChicken(int tag){
//        for (Chicken chicken : chickens){
//            if (chicken.isAlive && chicken.getTag() == tag){
//                return chicken;
//            }
//        }
//        return null;
//    }

    public ChickengroupRandom() throws IOException {
        randomChickenAdd();
    }

    public void randomChickenAdd() throws IOException {
        for (int i = 0; i < 10; i++) {
            centerx = random.nextInt(600) + 100;
            centery = random.nextInt(350) + 50;
            targetxs[i] = random.nextInt(600) + 200;
            targetys[i] = random.nextInt(350) + 50;
            float deltaX = (float) (targetxs[i] - centerx);
            float deltaY = (float) (targetys[i] - centery);
            float angle = (float) Math.atan2( deltaY, deltaX );
            chickens.add(new Chicken(centerx,centery,4 * Math.cos( angle )
                    ,4 * Math.sin( angle ),
                    0,0,random.nextInt(Rocket.chickensLevels)+1));
            chickens.get(i).tag = i;
//            chickens.get(i).ArriveToSource = true;
        }
    }

    @Override
    public void drawChickens(Graphics2D g2) {
        for (Chicken chicken : chickens) {
            chicken.paint(g2);
        }
    }

    public void move(){
        for (Chicken chicken : chickens) {
            if (chicken.hasTag()) {
            int i = chicken.getTag();
                if (Math.abs(chicken.pointx - targetxs[i]) < 1 ||
                    Math.abs(chicken.pointy - targetys[i]) < 1) {
                    chicken.pointx = targetxs[i];
                    chicken.pointy = targetys[i];
                    targetxs[i] = random.nextInt(600) + 200;
                    targetys[i] = random.nextInt(350) + 50;
                    float deltaX = (float) (targetxs[i] - chicken.pointx);
                    float deltaY = (float) (targetys[i] - chicken.pointy);
                    float angle = (float) Math.atan2(deltaY, deltaX);
                    chicken.vx = 4 * Math.cos(angle);
                    chicken.vy = 4 * Math.sin(angle);
                }
                chicken.pointx += chicken.vx;
                chicken.pointy += chicken.vy;
                chicken.move();
            }
        }

//        for (Chicken chicken : chickens) {
//            for (Egg egg : chicken.eggs) {
//                egg.move();
//            }
////            for (Coin coin : chicken.coins) {
//                chicken.coin.move();
////            }
////            for (PowerUp powerUp : chicken.powerUps) {
//                chicken.powerUp.move();
////            }
//        }
    }
}


























//    public void move(){
//        for (Chicken chicken : chickens) {
//            if (chicken.hasTag()) {
//                int i = chicken.getTag();
//                if (Math.abs(chicken.pointx - targetxs[i]) < 1 ||
//                        Math.abs(chicken.pointy - targetys[i]) < 1) {
//                    chicken.pointx = targetxs[i];
//                    chicken.pointy = targetys[i];
////                    if (chicken.MovingToTheRocket) {
//////                        System.out.println("move to source point");
////                        targetxs[i] = kooft[i];
////                        targetys[i] = zahr[i];
////                        float deltaX = (float) (targetxs[i] - chicken.pointx);
////                        float deltaY = (float) (targetys[i] - chicken.pointy);
////                        float angle = (float) Math.atan2(deltaY, deltaX);
////                        chicken.vx = 4 * Math.cos(angle);
////                        chicken.vy = 4 * Math.sin(angle);
////                        chicken.MovingToTheRocket = false;
////
////                        if (Math.abs(chicken.pointx - kooft[i]) < 1 ||
////                                Math.abs(chicken.pointy - zahr[i]) < 1) {
////                            chicken.ArriveToSource = true;
//////                            System.out.println("chicken arrived to rocket");
////                        }
////                    }
////                    else {
//                    targetxs[i] = random.nextInt(600) + 200;
//                    targetys[i] = random.nextInt(350) + 50;
//                    float deltaX = (float) (targetxs[i] - chicken.pointx);
//                    float deltaY = (float) (targetys[i] - chicken.pointy);
//                    float angle = (float) Math.atan2(deltaY, deltaX);
//                    chicken.vx = 4 * Math.cos(angle);
//                    chicken.vy = 4 * Math.sin(angle);
////                    }
////                    if (isTimeToMoveToTheRocket && chicken.ArriveToSource) {
////                        int w = random.nextInt(10);
////                        while (!tagToChicken(w).equals(null)
////                                && tagToChicken(w).equals(chicken.ArriveToSource)) {
////                            w = random.nextInt(10);
////                        }
////                        System.out.println("move to rocket "+w);
////                        kooft[w] = ((int) chicken.pointx);
////                        zahr[w] = ((int) chicken.pointy);
////                        targetxs[w] = rocket.getPointx();
////                        targetys[w] = rocket.getPointy();
////                        float deltaX = (float) (targetxs[w] - chicken.pointx);
////                        float deltaY = (float) (targetys[w] - chicken.pointy);
////                        float angle = (float) Math.atan2(deltaY, deltaX);
////                        chicken.vx = 4 * Math.cos(angle);
////                        chicken.vy = 4 * Math.sin(angle);
////                        chicken.MovingToTheRocket = true;
////                        chicken.ArriveToSource = false;
////                        isTimeToMoveToTheRocket = false;
////                    }
//                }
//                chicken.move();
//            }
//        }



//    Thread moveToRocket = new Thread(){
//        public void run(){
//            while (true) {
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e1) {
//                    e1.printStackTrace();
//                }
//                isTimeToMoveToTheRocket = true;
//
//            }
//        }
//    };