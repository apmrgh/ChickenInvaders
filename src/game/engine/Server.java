package game.engine;

import game.swing.MainPanel;


import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Server {

    private List<Rocket> rockets = Collections.synchronizedList(new ArrayList<>());
    private Stephandler stephandler;
    private Long lastWaveComplete = null;
    private ServerSocket serverSocket;
    private int port;
    private ArrayList<Clienthandler> clientName = new ArrayList<>();
    public boolean isloaded;
    private ArrayList<String> users;

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/Codes","root","");
    }

    public void saveToDB(){
        Connection connection;
        Statement statement;
        try {
            connection = getConnection();
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            saveToFile();
            return;
        }
        for (Rocket rocket : rockets) {
            try {
                statement.execute("update Users set score = " + rocket.score
                + ", bomb = " + (rocket.maxNumOfBombCanUse - rocket.numberOfBombUsed)
                + ", coin = " + rocket.coin
                + ", heart = " + rocket.heart
                + ", level = " + Rocket.chickensLevels
                + ", wave = " + Rocket.waveNumber
                + " where name = '" + rocket.name + "'");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveToFile(){
        ArrayList users = new ArrayList();
        for (Rocket rocket : rockets){
            users.add(rocket.name);
            users.add(rocket.score);
            users.add(rocket.maxNumOfBombCanUse
                    - rocket.numberOfBombUsed);
            users.add(rocket.coin);
            users.add(rocket.heart);
            users.add(Rocket.chickensLevels);
            users.add(Rocket.waveNumber);
            String s = rocket.name;
            PrintStream ps = null;

            try {
                ps = new PrintStream(new File("Data/" + s + ".data"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 7; i++) {
                ps.println(users.get(i));
            }
        }
    }


    public Rocket setClientRocket(ArrayList<String> users){
        if (users != null) {
            return new Rocket(users.get(0),
                    Integer.parseInt(users.get(1)),
                    Integer.parseInt(users.get(2)),
                    Integer.parseInt(users.get(3)),
                    Integer.parseInt(users.get(4)));
        }
        return null;
    }

    public Server(ArrayList<String> users) throws IOException {
        this.users = users;
        isloaded = true;
        port = 8152;
        setClientRocket(users);
        stephandler = new Stephandler(Integer.parseInt(users.get(5)),
                Integer.parseInt(users.get(6)));
        new Thread(this::acceptClient).start();
        updategame();
        checkalive.start();
        tempReduce.start();
    }

    public Server(int port) throws IOException {
        this.port = port;
        new Thread(this::acceptClient).start();
        stephandler = new Stephandler();
        updategame();
        checkalive.start();
        tempReduce.start();
    }

    private void acceptClient() {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                Socket socket = serverSocket.accept();
//                System.out.println("new Client Accept");
                new Clienthandler(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    class Clienthandler {
        Rocket rocket;
        private ObjectOutputStream objectOutputStream;
        private ObjectInputStream objectInputStream;
        public Clienthandler(Socket socket) {
            if (Client.hasRocket) {
                if (isloaded) {
                    rocket = setClientRocket(users);
                }else
                    rocket = new Rocket();
                rockets.add(rocket);
            }
            try {
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            clientName.add(this);
            new Thread(this::handle).start();
        }

        public void send(String string){
            try {
                ArrayList<String> strins=new ArrayList<>();
                strins.add("newUser");
                strins.add(string);
                objectOutputStream.writeObject(strins);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        private void sendNewUserToAllUsers(Clienthandler clienthandler1){
            for (Clienthandler clienthandler:clientName) {
                clienthandler.send(clienthandler1.rocket.name+" join the game");
            }
        }

        private void handle() {
            while (true) {
                try {
                    ArrayList requestFromClient = (ArrayList) objectInputStream.readObject();
                    if (requestFromClient.get(0).equals("Stephandler")) {
                        objectOutputStream.reset();
                        ArrayList arrayList1=new ArrayList();
                        arrayList1.add("step");
                        objectOutputStream.writeObject(arrayList1);
                        objectOutputStream.writeObject(stephandler);
                        objectOutputStream.writeObject(rockets);
                        ArrayList<String> arrayList = new ArrayList<>();
                        arrayList.add(String.valueOf(rocket.score));
                        arrayList.add(String.valueOf(rocket.maxNumOfBombCanUse
                                - rocket.numberOfBombUsed));
                        arrayList.add(String.valueOf(rocket.coin));
                        arrayList.add(String.valueOf(rocket.heart));
                        arrayList.add(String.valueOf(rocket.maxTemperature));
                        arrayList.add(String.valueOf(rocket.temp));
                        arrayList.add(String.valueOf(Rocket.chickensLevels));
                        arrayList.add(String.valueOf(Rocket.waveNumber));
                        arrayList.add(String.valueOf((int)Giant.heart));
                        objectOutputStream.writeObject(arrayList);
                    } else if (requestFromClient.get(0).equals("mouseMoved")) {
                        if (rocket.isalive) {
                            rocket.setPointx((Integer) requestFromClient.get(1));
                            rocket.setPointy((Integer) requestFromClient.get(2));
                        }
                    } else if (requestFromClient.get(0).equals("mouseDragged")) {
                        if (rocket.isalive) {
                            rocket.setPointx((Integer) requestFromClient.get(1));
                            rocket.setPointy((Integer) requestFromClient.get(2));
                        }
                    } else if (requestFromClient.get(0).equals("mouseClicked")) {
                        if (rocket.isalive) {
                            if (requestFromClient.get(1).equals(1)) {

                            } else if (requestFromClient.get(1).equals(3)) {
                                rocket.shelikbomb();
                            }
                        }
                    } else if (requestFromClient.get(0).equals("mousePressed")) {
                        if (rocket.isalive) {
                            if (requestFromClient.get(1).equals(1)) {
                                Rocket.a = true;
                                Thread thread = new Thread() {
                                    public void run() {
                                        while (Rocket.a) {
                                            if (rocket.isalive) {
                                                try {
                                                    rocket.sheliktir();
                                                } catch (IOException e1) {
                                                    e1.printStackTrace();
                                                }

                                            }
                                            try {
                                                Thread.sleep((long) rocket.firePeriod);
                                            } catch (InterruptedException e1) {
                                                e1.printStackTrace();
                                            }
                                        }
                                    }
                                };
                                thread.start();
                            }
                        }
                    } else if (requestFromClient.get(0).equals("mouseReleased")) {
                        Rocket.a = false;
                    } else if (requestFromClient.get(0).equals("plyerNamed")){
                        rocket.name = (String) requestFromClient.get(1);
                        sendNewUserToAllUsers(this);
                    }

                } catch (IOException e) {
                    rockets.remove(rocket);
//                    e.printStackTrace();
                    break;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }
    }










    public void updateHit() throws IOException {
        groupChickenHitTir();
        groupChickenHitRocket();
            RocketHitToChickenEggsAndCoinsAndPowerups();
        if (isCurrentGroupGiant()) {
            giantHitRocket();
            giantHitTir();
            RocketHitToGiantEggsAndPowerups();
        }
    }


    public void updategame() throws IOException {

        updateHit();

        for (Rocket rocket : rockets) {
            rocket.move();
        }
        stephandler.currentGroup.move();
        if (stephandler.waveComplete()) {
            if (lastWaveComplete == null) {
                lastWaveComplete = System.currentTimeMillis();
            } else if (System.currentTimeMillis()
                    - lastWaveComplete > 4000) {
                if (stephandler.currentGroup instanceof GiantHandler) {
                    for (Rocket rocket : rockets) {
                        rocket.score += rocket.coin * 3;
                        rocket.coin = 0;
                        rocket.maxNumOfBombCanUse++;
                    }
                }
                stephandler.waveChange();
                lastWaveComplete = null;
            }
        }
        try {
            if (stephandler.currentGroup instanceof GiantHandler) {
                ((GiantHandler) stephandler.currentGroup).updateGiant();
            } else {
                stephandler.currentGroup.updateChickengroup();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Rocket rocket : rockets) {
            if (rocket.isBombIsArriveToCenter()) {
                if (stephandler.currentGroup instanceof GiantHandler) {
                    ((GiantHandler) stephandler.currentGroup).giant.bombCrashGiant();
//                    System.out.println("salam");
                } else {
                    stephandler.currentGroup.chickenClear();
                    rocket.score +=
                            (stephandler.currentGroup.numberOfChicken - rocket.numberOfChikenKilled);
                }
            }
        }

    }


    Thread tempReduce = new Thread() {
        public void run() {
            while (true) {
                for (Rocket rocket : rockets) {
                    if (rocket.temp > 0 && !Rocket.a)
                        rocket.temp -= 1;
                }
                try {
                    Thread.sleep(25);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    };


    Thread checkalive = new Thread(() -> {
        while (true) {
//                System.out.println(""); TODO shoude be uncoment
            for (Rocket rocket : rockets) {
                if (!rocket.isalive) {
                    new Thread(() -> {
                        try {
                            Thread.sleep(2000);
//                            if (rocket.heart >= 0) {
                                rocket.isalive = true;
//                            }else
//                                System.exit(0);
                        } catch (InterruptedException e) {
                        }
                    }).start();

                    try {
                        Thread.sleep(25);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    });





    public void groupChickenHitTir() throws IOException {
        for (Chicken chicken : stephandler.currentGroup.chickens) {
            if (chicken.isAlive) {
                for (Rocket rocket : rockets) {
                    for (Tir tir : rocket.tirs) {
                        if (chicken.isAlive && tir.isalive) {
                            int a = (int) tir.getPointx() + chicken.width / 2;
                            int c = (int) tir.getPointy() + chicken.height / 2;
                            int b = chicken.getPointx() + chicken.width / 2 + 25;
                            int d = chicken.getPointy() + chicken.height / 2;
                            int sum = tir.width / 2;
                            int sum1 = tir.height / 2;
                            int sum2 = chicken.width / 2;
                            int sum3 = chicken.height / 2;
                            int sum4 = sum + sum2;
                            int sum5 = sum1 + sum3;
                            double f = chicken.width / 2 + 12;
                            int distance = (int) Math.sqrt((a - b) * (a - b) + (c - d) * (c - d));
                            if (distance <= f) {
                                if (chicken.heart > 0) {
                                    chicken.heart = chicken.heart - tir.power;
                                    if (chicken.heart <= 0) {
                                        chicken.addCoinAndPowerupWhenChickenDie();
                                        chicken.isAlive = false;
                                    }
                                    tir.isalive = false;
                                }
                            } // TODO chicken.chickenHitTir(tir);

                            if (!chicken.isAlive) {
                                rocket.score++;
                                rocket.numberOfChikenKilled++;
                            }
                        }
                    }
                }
            }
        }
    }

    public void groupChickenHitRocket() {
        for (Chicken chicken : stephandler.currentGroup.chickens) {
            if (chicken.isAlive) {
                for (Rocket rocket : rockets) {
                    if (rocket.isalive) {
                        int a = rocket.getPointx() + chicken.width / 2 - 38;
                        int c = rocket.getPointy() + chicken.height / 2 - 35;
                        int b = chicken.getPointx() + chicken.width / 2;
                        int d = chicken.getPointy() + chicken.height / 2;
                        int sum = rocket.width / 2;
                        int sum1 = rocket.height / 2;
                        int sum2 = chicken.width / 2;
                        int sum3 = chicken.height / 2;
                        int sum4 = sum + sum2;
                        int sum5 = sum1 + sum3;
                        double f = Math.sqrt((sum4 * sum4 + sum5 * sum5)) - 22;
                        int distance = (int) Math.sqrt((a - b) * (a - b) + (c - d) * (c - d));
                        if (distance <= f && rocket.isalive) {
                            rocket.isalive = false;
                            rocket.rocketHit();
                            chicken.isAlive = false;
                        }
                    }
                }
            }// TODO chicken.chickenHitRocket(rocket);
        }
    }

    public void RocketHitToChickenEggsAndCoinsAndPowerups() {
        for (Chicken chicken : stephandler.currentGroup.chickens) {
//            Coin coin = chicken.coin;
//            System.out.println(chicken.coin.equals(null));
//            System.out.println(coin.height);
//            PowerUp powerUp = chicken.powerUp;
            for (Egg egg : chicken.eggs) {
                if (egg.isAlive) {
                    for (Rocket rocket : rockets) {
                        if (rocket.isalive) {
                            int a = rocket.getPointx() + chicken.width / 2 - 40;
                            int c = rocket.getPointy() + chicken.height / 2;
                            int b = egg.pointx + egg.width / 2;
                            int d = egg.pointy + egg.height / 2;
                            int sum = rocket.width / 2;
                            int sum1 = rocket.height / 2;
                            int sum2 = egg.width / 2;
                            int sum3 = egg.height / 2;
                            int sum4 = sum + sum2;
                            int sum5 = sum1 + sum3;
                            double f = Math.sqrt(sum4 * sum4 + sum5 * sum5) - 35;
                            int distance = (int) Math.sqrt((a - b) * (a - b) + (c - d) * (c - d));
                            if (distance <= f) {
                                rocket.isalive = false;
                                System.out.println(rocket.heart);
                                rocket.rocketHit();
                                System.out.println("hit egg");
                                System.out.println(rocket.heart);
                                egg.isAlive = false;
                            }
                        }
                    }// TODO egg.eggHitRocket(rocket);
                }
            }
            for (Coin coin : chicken.coins) {
                if (coin.isAlive) {
                    for (Rocket rocket : rockets) {
                        if (rocket.isalive) {
                            for (Tir tir : rocket.tirs){
                                int a = (int) (tir.getPointx() + tir.width / 2 - 25);
                                int c = (int) (tir.getPointy() + tir.height / 2);
                                int b = coin.pointx + coin.width / 2;
                                int d = coin.pointy + coin.height / 2;
                                int distance = (int) Math.sqrt((a - b) * (a - b) + (c - d) * (c - d));
                                if (distance < 23) {
                                    coin.isAlive = false;
                                }
                            }
                            int a = rocket.getPointx() + coin.width / 2 - 25;
                            int c = rocket.getPointy() + coin.height / 2;
                            int b = coin.pointx + coin.width / 2;
                            int d = coin.pointy + coin.height / 2;
                            int sum = rocket.width / 2;
                            int sum1 = rocket.height / 2;
                            int sum2 = coin.width / 2;
                            int sum3 = coin.height / 2;
                            int sum4 = sum + sum2;
                            int sum5 = sum1 + sum3;
                            double f = Math.sqrt(sum4 * sum4 + sum5 * sum5) - 40;
                            int distance = (int) Math.sqrt((a - b) * (a - b) + (c - d) * (c - d));
                            if (distance <= f + 15) {
                                rocket.coin++;
                                coin.isAlive = false;
                            }
                        }
                    }// TODO coin.coinHitRocket(rocket);
                }
            }
            for (PowerUp powerUp : chicken.powerUps) {
                if (powerUp.isAlive) {
                    for (Rocket rocket : rockets) {
                        if (rocket.isalive) {
                            for (Tir tir : rocket.tirs){
                                int a = (int) (tir.getPointx() + tir.width / 2 - 25);
                                int c = (int) (tir.getPointy() + tir.height / 2);
                                int b = powerUp.pointx + powerUp.width / 2;
                                int d = powerUp.pointy + powerUp.height / 2;
                                int distance = (int) Math.sqrt((a - b) * (a - b) + (c - d) * (c - d));
                                if (distance < 23) {
                                    powerUp.isAlive = false;
                                }
                            }
                            int a = rocket.getPointx() + powerUp.width / 2 - 15;
                            int c = rocket.getPointy() + powerUp.height / 2 - 15;
                            int b = powerUp.pointx + powerUp.width / 2;
                            int d = powerUp.pointy + powerUp.height / 2;
                            int sum = rocket.width / 2;
                            int sum1 = rocket.height / 2;
                            int sum2 = powerUp.width / 2;
                            int sum3 = powerUp.height / 2;
                            int sum4 = sum + sum2;
                            int sum5 = sum1 + sum3;
                            double f = Math.sqrt(sum4 * sum4 + sum5 * sum5) - 40;
                            int distance = (int) Math.sqrt((a - b) * (a - b) + (c - d) * (c - d));
                            if (distance <= f + 15) {
                                if (powerUp.type == 1) {
                                    rocket.maxTemperature += 5;
                                } else if (powerUp.type == 2) {
                                    rocket.numOfTirPerShot++;
                                    rocket.numberOfPoweupType2++;
                                } else if (powerUp.type == 3) {
                                    if (rocket.tirType <= 3) {
                                        rocket.tirType++;
                                    }
                                }
                                powerUp.isAlive = false;
                            }
                        }
                    } // TODO powerUp.powerUpHitRocket(rocket);
                }
            }
        }
    }

    public boolean isCurrentGroupGiant() {
        return stephandler.currentGroup instanceof GiantHandler;
    }

    public void giantHitRocket() throws IOException {
        if (((GiantHandler) stephandler.currentGroup).giant.isAlive) {
            Giant giant = ((GiantHandler) stephandler.currentGroup).giant;
            for (Rocket rocket : rockets) {
                if (rocket.isalive) {
                    int a = rocket.getPointx() + giant.width / 2;
                    int c = rocket.getPointy() + giant.height / 2;
                    int b = giant.pointx + giant.width / 2;
                    int d = giant.pointy + giant.height / 2;
                    int sum = rocket.width / 2;
                    int sum1 = rocket.height / 2;
                    int sum2 = giant.width / 2;
                    int sum3 = giant.height / 2;
                    int sum4 = sum + sum2;
                    int sum5 = sum1 + sum3;
                    double f = Math.sqrt(sum4 * sum4 + sum5 * sum5) - 40;
                    int distance = (int) Math.sqrt((a - b) * (a - b) + (c - d) * (c - d));
                    if (distance <= f) {
                        if (giant.heart > 0) {
                            giant.heart = giant.heart - 50;
                            if (giant.heart <= 0) {
                                giant.addPowerupWhenGiantDie();
                                giant.isAlive = false;
                            }
                            rocket.isalive = false;
                            rocket.rocketHit();
                        }
                    }
                }
            }// TODO giant.giantHitRocket(rocket);
        }
    }

    public void giantHitTir() throws IOException {
        if (((GiantHandler) stephandler.currentGroup).giant.isAlive) {
            Giant giant = ((GiantHandler) stephandler.currentGroup).giant;
            for (Rocket rocket : rockets) {
                for (Tir tir : rocket.tirs) {
                    if (giant.isAlive) {
                        if (tir.isalive) {
                            int a = (int) tir.getPointx() + giant.width / 2;
                            int c = (int) tir.getPointy() + giant.height / 2;
                            int b = giant.getPointx() + giant.width / 2;
                            int d = giant.getPointy() + giant.height / 2;
                            int sum = tir.width / 2;
                            int sum1 = tir.height / 2;
                            int sum2 = giant.width / 2;
                            int sum3 = giant.height / 2;
                            int sum4 = sum + sum2;
                            int sum5 = sum1 + sum3;
                            double f = Math.sqrt(sum4 * sum4 + sum5 * sum5);
                            int distance = (int) Math.sqrt((a - b) * (a - b) + (c - d) * (c - d));
                            if (distance <= f) {
                                if (giant.heart > 0) {
                                    giant.heart = giant.heart - tir.power;
                                    if (giant.heart <= 0) {
                                        giant.addPowerupWhenGiantDie();
                                        giant.isAlive = false;
                                    }
                                    tir.isalive = false;
                                }
                            }
                        }// TODO giant.giantHitTir(tir);
                    }
                }
            }
        }
    }

    public void RocketHitToGiantEggsAndPowerups() {
        Giant giant = ((GiantHandler) stephandler.currentGroup).giant;
        for (Egg egg : giant.eggs) {
            if (egg.isAlive) {
                for (Rocket rocket : rockets) {
                    if (rocket.isalive) {
                        int a = rocket.getPointx() + giant.width / 2 - 12;
                        int c = rocket.getPointy() + giant.height / 2;
                        int b = giant.pointx + giant.width / 2;
                        int d = giant.pointy + giant.height / 2;
                        int sum = rocket.width / 2;
                        int sum1 = rocket.height / 2;
                        int sum2 = giant.width / 2;
                        int sum3 = giant.height / 2;
                        int sum4 = sum + sum2;
                        int sum5 = sum1 + sum3;
                        double f = Math.sqrt(sum4 * sum4 + sum5 * sum5) - 35;
                        int distance = (int) Math.sqrt((a - b) * (a - b) + (c - d) * (c - d));
                        if (distance <= f) {
                            rocket.isalive = false;
                            rocket.rocketHit();
                            giant.isAlive = false;
                        }
                    }
                }// TODO egg.eggHitRocket(rocket);
            }
        }

        for (PowerUp powerUp : ((GiantHandler) stephandler.currentGroup).giant.powerUps) {
            if (powerUp.isAlive) {
                for (Rocket rocket : rockets) {
                    if (rocket.isalive) {
                        int a = rocket.getPointx() + powerUp.width / 2 - 15;
                        int c = rocket.getPointy() + powerUp.height / 2 - 15;
                        int b = powerUp.pointx + powerUp.width / 2;
                        int d = powerUp.pointy + powerUp.height / 2;
                        int sum = rocket.width / 2;
                        int sum1 = rocket.height / 2;
                        int sum2 = powerUp.width / 2;
                        int sum3 = powerUp.height / 2;
                        int sum4 = sum + sum2;
                        int sum5 = sum1 + sum3;
                        double f = Math.sqrt(sum4 * sum4 + sum5 * sum5) - 40;
                        int distance = (int) Math.sqrt((a - b) * (a - b) + (c - d) * (c - d));
                        if (distance <= f + 15) {
                            if (powerUp.type == 1) {
                                rocket.maxTemperature += 5;
                            } else if (powerUp.type == 2) {
                                rocket.numOfTirPerShot++;
                                rocket.numberOfPoweupType2++;
                            } else if (powerUp.type == 3) {
                                if (rocket.tirType <= 3) {
                                    rocket.tirType++;
                                }
                            }
                            powerUp.isAlive = false;
                        }
                    }
                }//TODO powerUp.powerUpHitRocket(rocket);
            }
        }
    }

}






















//    Thread thread = new Thread(){
//        @Override
//        public void run() {
//            while (true) {
//                progressBar.setMaximum(Rocket.maxTemperature);
//                progressBar.setValue(Rocket.temp);
//                try {
//                    sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    };
//        thread.start();

    //    public void move() {
//        rocket.move();
//        stephandler.currentGroup.move();
//    }


//    @Override
//    public void paint(Graphics2D g2) {
//
////        g2.drawImage(backpic, 0, 0,1000,800, null);
//
////        rocket.paint(g2);
//
//        if (stephandler.currentGroup instanceof GiantHandler){
//            ((GiantHandler) stephandler.currentGroup).drawGiants(g2);
//        }else {
//            stephandler.currentGroup.drawChickens(g2);
//        }
//
//        jLabelscore.repaint();
//
//        jLabelbomb.repaint();
//
//        jLabelhealth.repaint();
//
//        jLabelcoin.repaint();
//
//
//
//        g2.drawImage(bombpic,5,700,55,55,null);
//        jLabelbomb.setBounds(60,700,55,55);
//        g2.drawImage(coinpic,115,700,55,55,null);
//        jLabelcoin.setBounds(180,700,55,55);
//        g2.drawImage(healthpic,225,700,55,55,null);
//        jLabelhealth.setBounds(280,700,55,55);
//
//    }

//    public void setjlables(){
//        jLabelscore = new JLabel();
//        jLabelscore.setBounds(110,0,55,55);
//        jLabelscore.setForeground(Color.WHITE);
//        jLabelscore.setFont(new Font("Serif", Font.BOLD, 35));
//        jLabelscore.setText(String.valueOf(rocket.score));
//
//        jLabelbomb = new JLabel("first jlable");
//        jLabelbomb.setForeground(Color.WHITE);
//        jLabelbomb.setFont(new Font("Serif", Font.BOLD, 35));
//        jLabelbomb.setText(String.valueOf(rocket.maxNumOfBombCanUse
//                                        - rocket.numberOfBombUsed));
//
//        jLabelcoin = new JLabel("second jlable");
//        jLabelcoin.setForeground(Color.WHITE);
//        jLabelcoin.setFont(new Font("Serif", Font.BOLD, 35));
//        jLabelcoin.setText(String.valueOf(0));
//
//        jLabelhealth = new JLabel("third jlable");
//        jLabelhealth.setForeground(Color.WHITE);
//        jLabelhealth.setFont(new Font("Serif", Font.BOLD, 35));
//        jLabelhealth.setText(String.valueOf(rocket.heart));
//    }

