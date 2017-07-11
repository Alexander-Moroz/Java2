package ru.geekbrains.java2.dz.dz8.alexandermoroz.server;

import javax.swing.*;

public class MainClass {
    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setBounds(500, 500, 100, 100);
        jf.setTitle("Server");
        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        SQLHandler.connect();
        MyServer w = new MyServer();
    }
}