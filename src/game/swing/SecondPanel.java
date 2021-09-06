package game.swing;

import game.PaintLoop;
import game.engine.BufferedFiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;


public class SecondPanel extends JPanel implements ActionListener {

    JButton continuebutton = new JButton("Continue");
    JButton newbutton = new JButton("New Game");
    JButton settingbutton = new JButton("Setting");
    JButton rankingbutton = new JButton("Ranking");
    JButton exitbutton = new JButton("Exit");
    JButton aboutusbutton = new JButton("About Us");
    MainFrame mainFrame;
    JLabel username = new JLabel();
    public String plyerName;

//    BufferedImage backpic = ImageIO.read(new File("resources/lastback2.jpg"));

    public SecondPanel(MainFrame mframe, String userName) throws IOException {
        username.setSize(300,180);
        username.setLocation(320,20);
        plyerName = userName;
        username.setText(plyerName);
        username.setForeground(Color.WHITE);
        username.setFont(new Font("Serif", Font.PLAIN, 30));
        add(username);
        mainFrame = mframe;
        continuebutton.setSize(180,60);
        continuebutton.setLocation(410,370);
        newbutton.setSize(180,60);
        newbutton.setLocation(410,440);
        settingbutton.setSize(180,60);
        settingbutton.setLocation(410,510);
        rankingbutton.setSize(180,60);
        rankingbutton.setLocation(410,580);
        exitbutton.setSize(180,60);
        exitbutton.setLocation(125,650);
        aboutusbutton.setSize(180,60);
        aboutusbutton.setLocation(695,650);
        setVisible(true);
        setLocation(0,0);
        setSize(1000,800);
        setLayout(new FlowLayout());
        add(continuebutton);
        add(newbutton);
        add(settingbutton);
        add(rankingbutton);
        add(exitbutton);
        add(aboutusbutton);


        aboutusbutton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                    "I Am MohammadReza Ghirbani");
        });

        exitbutton.addActionListener(e -> {
            System.exit(0);
        });

        newbutton.addActionListener(e -> {
            MultiPlayerPanel multiPlayerPanel =
                    new MultiPlayerPanel(mainFrame, plyerName);
            mainFrame.remove(this);
            mainFrame.add(multiPlayerPanel);
            mainFrame.repaint();
        });

        if (new File("Data/" + plyerName + ".data").exists()) {
            continuebutton.addActionListener(this);
        }else
            continuebutton.setEnabled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(BufferedFiles.panels_Image, 0, 0, 1000,800,null);
    }

    public ArrayList<String> loadFromFile(String un) {
        ArrayList<String> users = new ArrayList();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("Data/" + un + ".data"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 7; i++) {
            String filegg = scanner.nextLine();
            users.add(filegg);
        }
        return users;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == continuebutton){
            MainPanel mainPanel = null;
            try {
                mainPanel = new MainPanel(loadFromDB(), plyerName);
//                mainPanel = new MainPanel(loadFromFile(plyerName), plyerName);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            mainFrame.remove(this);
            mainFrame.myAdd(mainPanel);
            try {
                new PaintLoop(mainPanel).start();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }


    private ArrayList<String> loadFromDB(){
        ArrayList<String> users = new ArrayList();
        Connection connection;
        Statement statement;
        try {
            connection = getConnection();
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("gand zadi");
            return loadFromFile("salam");
        }
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery
                    ("select * from Users where name='" + "Mohammad Reza" + "'");
            resultSet.next();
            users.add(resultSet.getString(1));
            for (int i = 2; i < 8; i++) {
                users.add(String.valueOf(resultSet.getInt(i)));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/Codes","root","");
    }


}
