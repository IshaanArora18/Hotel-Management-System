package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import main.database.SqlSetUP;
import main.resources.ColorPallete;


public class SplashScreen {
	

    JWindow splashWindow = new JWindow();
    JProgressBar progressBar = new JProgressBar();

    SplashScreen() throws IOException{

        splashWindow.setSize(450, 450);
        splashWindow.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(new ColorPallete().getC2cream());
        splashWindow.add(panel);
        panel.setLayout(new FlowLayout());

        JLabel label = new JLabel();     
        ImageIcon img = new ImageIcon("src/main/resources/Boyo_logo.png");        
        label.setBorder(new EmptyBorder(100, 0, 50,0));
        label.setIcon(img);
        panel.add(label, JLabel.CENTER);
        
        progressBar.setStringPainted(true);
        progressBar.setBackground(Color.white);
        progressBar.setForeground(new ColorPallete().getC9darkBlue());
        progressBar.setPreferredSize(new Dimension(300, 15));
        panel.add(progressBar);

        splashWindow.setVisible(true);

        //all happening after this is while loading is happening
        fill();

        splashWindow.setVisible(false);

        JPasswordField pf = new JPasswordField();
        int okCxl = JOptionPane.showConfirmDialog(new JFrame(), pf, "Enter mySQL root Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (okCxl == JOptionPane.OK_OPTION) {
            CurrentUser.sqlPassword = new String(pf.getPassword());
            System.err.println("You entered: " + CurrentUser.sqlPassword);
        }
        else {
            JOptionPane.showMessageDialog(new JFrame(), "SQL root password needed!!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        new SqlSetUP("boyodb", "root", CurrentUser.sqlPassword);

        new WelcomePage();

        splashWindow.dispose();
    }

    private void fill(){
        int count = 0;
        while(count<=100){
            progressBar.setValue(count);
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
        }
    }
	
}