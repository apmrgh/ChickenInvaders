package game.engine;

import game.engine.Animatable;
import game.engine.BufferedFiles;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Bomb implements Animatable, Serializable {

    private double pointx;
    private double pointy;
    private double vx;
    private double vy;
    public final int width = loadFFile();
    public final int height = loadFFile();
    public boolean isalive;
    public boolean isArriveToCenter = false;

    public int loadFFile() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(
                    new File("ConfigurationFile/ConFile.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(scanner.nextLine());
    }

    public Bomb (double pointx, double pointy, double vx, double vy) {
        this.pointx = pointx;
        this.pointy = pointy;
        this.vx = vx;
        this.vy = vy;
        isalive = true;

        AffineTransform tx = new AffineTransform();
        tx.rotate(Math.atan2(vy, vx), BufferedFiles.Bomb_Image.getWidth() / 2,
                BufferedFiles.Bomb_Image.getHeight() / 2);
        AffineTransformOp op = new AffineTransformOp(tx,
                AffineTransformOp.TYPE_BILINEAR);
        BufferedFiles.Bomb_Image = op.filter(BufferedFiles.Bomb_Image, null);
    }

    public void paint(Graphics2D g2) {
        if (isalive)
            g2.drawImage(BufferedFiles.Bomb_Image, (int)pointx, (int)pointy
                    ,width,height, null);
    }

    public void move() {
        if (isalive) {
            pointx += vx;
            pointy += vy;
        }
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
