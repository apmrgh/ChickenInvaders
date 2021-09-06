package xo;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        MainPanel mainPanel = new MainPanel();
        mainFrame.add(mainPanel);


//        try {
//            FileOutputStream fileOutputStream_wave =
//                    new FileOutputStream("/Users/macbook/IdeaProjects/ChickenInvaders/data/"
//                            + name + "_wave.res");
//            ObjectOutputStream objectOutputStream_wave = new ObjectOutputStream(fileOutputStream_wave);
//            objectOutputStream_wave.writeObject(waveHandeler);
//
//            objectOutputStream_wave.close();
//            fileOutputStream_wave.close();
//
//            FileOutputStream fileOutputStream_rocket =
//                    new FileOutputStream("/Users/macbook/IdeaProjects/ChickenInvaders/data/" +
//                            name + "_rocket.res");
//            ObjectOutputStream objectOutputStream_rocket = new ObjectOutputStream(fileOutputStream_rocket);
//            objectOutputStream_rocket.writeObject(rockets.get(0));
//
//            objectOutputStream_rocket.close();
//            fileOutputStream_rocket.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }



        /*
        File file = new File("readme.txt");

//        FileReader inf = null;
//        inf = new FileReader("readme.txt");
//        int infint = inf.read();
//
//        inf.close();
//
//        FileWriter writer = new FileWriter("writeme.txt");
//        writer.write("salam azizam /n");
//        writer.close();


        String adress = new File("").getAbsolutePath();
        try {
            FileOutputStream fout = new FileOutputStream("gg.txt");
            PrintWriter pw = new PrintWriter(fout);
            pw.println("salam\ndaneshjo\nkhob");
            pw.flush();

            FileInputStream fin = new FileInputStream("gg.txt");
            Scanner s = new Scanner(fin);
            while (s.hasNext()) {
                String filegg = s.nextLine();
                System.out.println(filegg);
            }


        } catch (Exception e) {
            System.out.println("gand zadi!");
            e.printStackTrace();
        }
        */






    }
}
