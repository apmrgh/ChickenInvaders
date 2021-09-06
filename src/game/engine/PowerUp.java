package game.engine;

import game.engine.Animatable;
import game.engine.BufferedFiles;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class PowerUp implements Animatable, Serializable {

    public int pointx;
    public int pointy;
    private int vy;
    public int width = 30;
    public int height = 30;
    public boolean isAlive;
    public int type;

    public PowerUp(int pointx, int pointy, int vy, int type) throws IOException {
        this.pointx = pointx;
        this.pointy = pointy;
        this.vy = vy;
        isAlive = true;
        this.type = type;
//        if (type == 1){
//            bufferedImage = ImageIO.read(new File("resources/powerup1.png"));
//        }else if (type == 2){
//            bufferedImage = ImageIO.read(new File("resources/powerup2.png"));
//        }else if (type == 3){
//            bufferedImage = ImageIO.read(new File("resources/powerup3.jpeg"));
//        }
    }


    @Override
    public void paint(Graphics2D g2) {
        if (isAlive) {
            if (type == 1){
                g2.drawImage(BufferedFiles.PowerUPType1_Image, pointx, pointy, width, height, null);
            }else if (type == 2){
                g2.drawImage(BufferedFiles.PowerUPType2_Image, pointx, pointy, width, height, null);
            }else if (type == 3){
                g2.drawImage(BufferedFiles.PowerUPType3_Image, pointx, pointy, width, height, null);
            }
//            g2.drawImage(bufferedImage, pointx, pointy, width, height, null);
        }
    }

    @Override
    public void move() {
        if (isAlive)
            pointy += vy;
    }
}
