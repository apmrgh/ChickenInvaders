package game.engine;

import game.Data.DataFile;

import java.io.IOException;
import java.io.Serializable;

public class Stephandler implements Serializable {

    Chickengroup currentGroup;

    public Stephandler(int level, int wave) throws IOException {
        Rocket.chickensLevels = level;
        if (wave == 1){
            currentGroup =
                    new ChickengroupRectangle(100,100);
            Rocket.waveNumber = 1;
        }else if (wave == 2){
            currentGroup =
                    new ChickengroupCircle(500, 400);
            Rocket.waveNumber = 2;
        }else if (wave == 3){
            currentGroup =
                    new ChickengroupCenter(500, 400);
            Rocket.waveNumber = 3;
        }else if (wave == 4){
            currentGroup =
                    new ChickengroupRandom();
            Rocket.waveNumber = 4;
        }else if (wave == 5){
            currentGroup = new GiantHandler();
            Rocket.waveNumber = 5;
        }

    }


    public Stephandler() throws IOException {
        Rocket.chickensLevels = 1;
        Rocket.waveNumber = 1;
        currentGroup =
        new ChickengroupRectangle(100,100);


//        currentGroup =
//        new ChickengroupCircle(500, 400);

//        currentGroup =
//        new ChickengroupCenter(500, 400);

//        currentGroup =
//        new ChickengroupRandom();

//        currentGroup = new GiantHandler();
    }


    public void waveChange() throws IOException {
        if (currentGroup instanceof ChickengroupRectangle) {
            Rocket.waveNumber = 2;
            currentGroup = new ChickengroupCircle(500, 400);
        } else if (currentGroup instanceof ChickengroupCircle) {
            Rocket.waveNumber = 3;
            currentGroup = new ChickengroupCenter(500, 400);
        } else if (currentGroup instanceof ChickengroupCenter) {
            Rocket.waveNumber = 4;
            currentGroup = new ChickengroupRandom();
        } else if (currentGroup instanceof ChickengroupRandom) {
            Rocket.waveNumber = 5;
            currentGroup = new GiantHandler();
        } else if (currentGroup instanceof GiantHandler) {
            if (Rocket.chickensLevels < 4) {
                Rocket.chickensLevels++;
            }else {
//                dataFile.savedf();


//                    TODO پیام تموم شدن بازی نشون داده بشه
            }
            Rocket.waveNumber = 1;
            currentGroup = new ChickengroupRectangle(100, 100);
        }
    }


    public boolean waveComplete(){

        if (currentGroup instanceof ChickengroupRectangle){
            return currentGroup.isOver();
        }else if (currentGroup instanceof ChickengroupCircle){
            return currentGroup.isOver();
        }else if (currentGroup instanceof ChickengroupCenter){
            return currentGroup.isOver();
        }else if (currentGroup instanceof ChickengroupRandom){
            return currentGroup.isOver();
        }else if (currentGroup instanceof GiantHandler){
            return ((GiantHandler) currentGroup).isOverGiant();
        }
        return false;
    }

}
