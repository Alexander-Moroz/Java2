package ru.geekbrains.java2.dz.dz8.alexandermoroz.client;

import javax.swing.*;
import java.awt.*;

public class AboutWindow extends JFrame {
    public AboutWindow() throws HeadlessException {
        super("About Messenger");
        setBounds(100, 100, 200, 200);
        setSize(200, 100);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }

    public void paint (Graphics g) {
        g.setColor(Color.BLUE);
        g.drawString("Easy Messenger", 50, 40);
        g.setColor(Color.BLACK);
        g.drawString("Author: FIO", 20, 60);
        g.setColor(Color.GRAY);
        g.drawString("GB, 2017", 45, 80);
    }
}