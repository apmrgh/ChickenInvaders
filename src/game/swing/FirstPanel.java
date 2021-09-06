package game.swing;

import game.engine.BufferedFiles;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

public class FirstPanel extends JPanel implements ActionListener{

    JButton addplayer = new JButton("Add Player");
    JButton deleteplayer = new JButton("Delete Player");
    JButton loginplayer = new JButton("Login");
    ArrayList<JRadioButton> users = new ArrayList<>();
    ArrayList<JTextField> textFields = new ArrayList<>();
    MainFrame mainFrame;
//    BufferedImage backpic = ImageIO.read(new File("resources/star.png"));
    ButtonGroup bg=new ButtonGroup();
    public JScrollPane sc;




    public FirstPanel() throws IOException {
        BufferedFiles.load();
//        try {
//            backpic = ImageIO.read(new File("resources/back1.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

         sc = new JScrollPane(this,
                 JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                 JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
         setBounds(200,200,200,600);
         sc.setVisible(true);

        mainFrame = new MainFrame();
        mainFrame.add(this);
        mainFrame.add(sc);
        setLocation(0,0);
        setSize(1000,800);
        setLayout(null);


        JRadioButton oldplayer1 = new JRadioButton("Mohammad Reza");
        oldplayer1.setForeground(Color.WHITE);
        oldplayer1.setSize(250,30);
        oldplayer1.setLocation(390,120);
        oldplayer1.setFont(new Font("Serif", Font.PLAIN, 25));
        users.add(oldplayer1);
        add(oldplayer1);

        JRadioButton oldplayer2 = new JRadioButton("Mohammad Mahdi");
        oldplayer2.setForeground(Color.WHITE);
        oldplayer2.setSize(250,30);
        oldplayer2.setLocation(390,40 + 120);
        oldplayer2.setFont(new Font("Serif", Font.PLAIN, 25));
        users.add(oldplayer2);
        add(oldplayer2);

        for (JRadioButton jRadioButton : users){
            bg.add(jRadioButton);
        }


        add(deleteplayer);
        deleteplayer.setSize(180,60);
        deleteplayer.setLocation(410,650);

        add(loginplayer);
        loginplayer.setSize(180,60);
        loginplayer.setLocation(695,650);

        add(addplayer);
        addplayer.setSize(180,60);
        addplayer.setLocation(125,650);


        loginplayer.addActionListener(this);

        addplayer.addActionListener(e -> {
            JTextField newplayer = new JTextField("write");
            newplayer.setBackground(Color.cyan);
            newplayer.setSize(250,30);
            int m = textFields.size();
            System.out.println(m);
            newplayer.setLocation(390,40*m + 200);
            textFields.add(newplayer);
            add(newplayer);


            newplayer.addActionListener(e1 -> {
                String name = newplayer.getText();


                JRadioButton player = new JRadioButton(name);
                player.setForeground(Color.WHITE);
                player.setLocation(newplayer.getLocation());
                player.setSize(newplayer.getSize());
                player.setFont(new Font("Serif", Font.PLAIN, 25));
                remove(newplayer);
                users.add(player);
                for (JRadioButton jRadioButton : users){
                    bg.add(jRadioButton);
                }
                add(player);
                repaint();

            });

        });
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(BufferedFiles.panels_Image, 0, 0, 1000,800,null);
    }

    public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = getSelectedButtonText(bg);
        if (!name.equals("")) {
            SecondPanel secondPanel = null;
            try {
                secondPanel = new SecondPanel(mainFrame, name);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            mainFrame.remove(this);
            mainFrame.add(secondPanel);
            mainFrame.repaint();
        }
    }
}
