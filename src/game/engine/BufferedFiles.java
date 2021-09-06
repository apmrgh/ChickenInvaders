package game.engine;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BufferedFiles {

    public static BufferedImage Bomb_Image;
    public static BufferedImage ChickenType1_Image;
    public static BufferedImage ChickenType2_Image;
    public static BufferedImage ChickenType3_Image;
    public static BufferedImage ChickenType4_Image;
    public static BufferedImage Coin_Image;
    public static BufferedImage Egg_Image;
    public static BufferedImage Giant_Image;
    public static BufferedImage PowerUPType1_Image;
    public static BufferedImage PowerUPType2_Image;
    public static BufferedImage PowerUPType3_Image;
    public static BufferedImage Rocket_Image;
    public static BufferedImage TirType1_Image;
    public static BufferedImage TirType2_Image;
    public static BufferedImage TirType3_Image;
    public static BufferedImage backpic;
    public static BufferedImage bombpic;
    public static BufferedImage coinpic;
    public static BufferedImage healthpic;
    public static BufferedImage panels_Image;


    public static void load() throws IOException {
        Bomb_Image = ImageIO.read(new File("resources/bomb.png"));
        ChickenType1_Image = ImageIO.read(new File("resources/chick1.png"));
        ChickenType2_Image = ImageIO.read(new File("resources/chick2.png"));
        ChickenType3_Image = ImageIO.read(new File("resources/chick3.png"));
        ChickenType4_Image = ImageIO.read(new File("resources/chick4.png"));
        Coin_Image = ImageIO.read(new File("resources/coin.png"));
        Egg_Image = ImageIO.read(new File("resources/egg.png"));
        Giant_Image = ImageIO.read(new File("resources/giant.png"));
        PowerUPType1_Image = ImageIO.read(new File("resources/powerup1.png"));
        PowerUPType2_Image = ImageIO.read(new File("resources/powerup2.png"));
        PowerUPType3_Image = ImageIO.read(new File("resources/powerup3.jpeg"));
        Rocket_Image = ImageIO.read(new File("resources/rocket.png"));
        TirType1_Image = ImageIO.read(new File("resources/bullet1.png"));
        TirType2_Image = ImageIO.read(new File("resources/bullet2.png"));
        TirType3_Image = ImageIO.read(new File("resources/bullet3.png"));
        backpic = ImageIO.read(new File("resources/game_back.jpg"));
        bombpic = ImageIO.read(new File("resources/rocket6.png"));
        coinpic = ImageIO.read(new File("resources/coin.png"));
        healthpic = ImageIO.read(new File("resources/heart.png"));
        panels_Image = ImageIO.read(new File("resources/lastback2.jpg"));
    }



}
