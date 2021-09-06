package game.Data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class DataFile {


    public static void savedf(String playerName){
        FileOutputStream fout = null;
        try {
            fout = new
                    FileOutputStream("Data/playerName.txt");
        } catch (FileNotFoundException e1) {
            System.out.println("gand zadi!");

            e1.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fout);
        pw.println("salam\ndaneshjo\nkhob");
        pw.flush();
    }


    public static void loaddf(String playerName){
        FileInputStream fin = null;
        try {
            fin = new FileInputStream("Data/playerName.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner s = new Scanner(fin);
        while (s.hasNext()) {
            String filegg = s.nextLine();
            System.out.println(filegg);
        }
    }


}
