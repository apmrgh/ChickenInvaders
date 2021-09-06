package game.engine;

import javax.swing.*;

public class TempBar {

    public JProgressBar progressBar;

    public TempBar() {
        progressBar = new JProgressBar();
//        progressBar = new JProgressBar(0,Rocket.maxTemperature);
        progressBar.setSize(100,20);
        progressBar.setLocation(5,15);

//        progressBar.setBorderPainted(true);

//        Thread thread = new Thread(){
//            @Override
//            public void run() {
//                while (true) {
//                    progressBar.setMaximum(Rocket.maxTemperature);
//                    progressBar.setValue(Rocket.temp);
//                    try {
//                        sleep(1);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        };
//        thread.start();


    }
}
