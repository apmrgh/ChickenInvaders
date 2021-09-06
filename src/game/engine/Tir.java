package game.engine;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class Tir implements Animatable , Serializable {

    private double pointx;
    private double pointy;
    private double vx;
    private double vy;
    public int width = 20;
    public int height = 20;
    public boolean isalive;
    public int type;
    public int firingperiod;
    public double power;
    public double increaseTempPerShot;

    public Tir(double pointx, double pointy, double vx, double vy,
               int type, int numberOfPoweupType2) throws IOException {
        this.pointx = pointx;
        this.pointy = pointy;
        this.vx = vx;
        this.vy = vy;
        isalive = true;
        this.type = type;

        int a = numberOfPoweupType2;
        if (a == 0){
            a = 1;
        }
        if (numberOfPoweupType2 >= 4) {
            a = 4;
        }
        if (type == 1){
            increaseTempPerShot = 5/a;
            power = 1;
            AffineTransform tx = new AffineTransform();
            tx.rotate(Math.atan2(vy, vx), BufferedFiles.TirType1_Image.getWidth() / 2,
                    BufferedFiles.TirType1_Image.getHeight() / 2);
            AffineTransformOp op = new AffineTransformOp(tx,
                    AffineTransformOp.TYPE_BILINEAR);
            BufferedFiles.TirType1_Image = op.filter(BufferedFiles.TirType1_Image, null);
        }else if (type == 2){
            increaseTempPerShot = 4/a;
            power = 2;
        }else {
            increaseTempPerShot = 3/a;
            power = 3;
        }

        if (numberOfPoweupType2 >=4){
            power += (int) (power*0.25 * (numberOfPoweupType2 - 3));
        }
//        Rocket.temp += increaseTempPerShot;

    }

    public void paint(Graphics2D g2) {
        if (isalive) {
            if (type == 1){
//                System.out.println("hvbjnkm");
//                AffineTransform tx = new AffineTransform();
//                tx.rotate(Math.atan2(vy, vx), BufferedFiles.TirType1_Image.getWidth() / 2,
//                        BufferedFiles.TirType1_Image.getHeight() / 2);
//                AffineTransformOp op = new AffineTransformOp(tx,
//                        AffineTransformOp.TYPE_BILINEAR);
//                BufferedFiles.TirType1_Image = op.filter(BufferedFiles.TirType1_Image, null);
                g2.drawImage(BufferedFiles.TirType1_Image, (int) pointx - width / 2, (int) pointy - height / 2, width, height, null);
            }else if (type == 2){
//                AffineTransform tx = new AffineTransform();
//                tx.rotate(Math.atan2(vy, vx), BufferedFiles.TirType2_Image.getWidth() / 2,
//                        BufferedFiles.TirType2_Image.getHeight() / 2);
//                AffineTransformOp op = new AffineTransformOp(tx,
//                        AffineTransformOp.TYPE_BILINEAR);
//                BufferedFiles.TirType2_Image = op.filter(BufferedFiles.TirType2_Image, null);
                g2.drawImage(BufferedFiles.TirType2_Image, (int) pointx - width / 2, (int) pointy - height / 2, width, height, null);
            }else {
//                AffineTransform tx = new AffineTransform();
//                tx.rotate(Math.atan2(vy, vx), BufferedFiles.TirType3_Image.getWidth() / 2,
//                        BufferedFiles.TirType3_Image.getHeight() / 2);
//                AffineTransformOp op = new AffineTransformOp(tx,
//                        AffineTransformOp.TYPE_BILINEAR);
//                BufferedFiles.TirType3_Image = op.filter(BufferedFiles.TirType3_Image, null);
                g2.drawImage(BufferedFiles.TirType3_Image, (int) pointx - width / 2, (int) pointy - height / 2, width, height, null);
            }

//            g2.drawImage(bufferedImage, (int) pointx - width / 2, (int) pointy - height / 2, width, height, null);
        }
    }

    public void move() {
        if (isalive) {
            pointx += vx;
            pointy += vy;
        }
    }

    public int getFiringperiod() {
        return firingperiod;
    }

    public void setFiringperiod(int firingperiod) {
        this.firingperiod = firingperiod;
    }

    public double getPointx() {
        return pointx;
    }

    public void setPointx(double pointx) {
        this.pointx = pointx;
    }

    public double getPointy() {
        return pointy;
    }

    public void setPointy(double pointy) {
        this.pointy = pointy;
    }
}
