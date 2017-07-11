package ru.geekbrains.java2.dz.dz8.alexandermoroz.client;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;

public class ChangePasswordWindow extends JFrame {
    final String SERVER_ADDR = "localhost";
    final int SERVER_PORT = 1111;
    Socket socket;
    DataInputStream in;
    DataOutputStream out;

    public ChangePasswordWindow() throws HeadlessException {
        super("Change Password");
        setSize(600, 70);
        setResizable(false);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        FlowLayout layout = new FlowLayout();
        JPanel addNewUserPanel = new JPanel(layout);
        setLayout(layout);

        JLabel labelLogin = new JLabel("Login");
        JTextField jtfLogin = new JTextField("");
        JLabel labelPassword = new JLabel("Password");
        JTextField jtfPassword = new JTextField("");
        JButton jbChangePassword = new JButton("Change Password");

        jtfLogin.setEditable(true);
        jtfPassword.setEditable(true);

        addNewUserPanel.add(labelLogin);
        addNewUserPanel.add(jtfLogin);
        addNewUserPanel.add(labelPassword);
        addNewUserPanel.add(jtfPassword);
        addNewUserPanel.add(jbChangePassword);
        add(addNewUserPanel, layout);

        jbChangePassword.addActionListener(e -> {
            String str = "change Password\t" + jtfLogin.getText() + "\t" + jtfPassword.getText();
            System.out.println(str);
            connect(str);
        });

        setVisible(true);
    }

    public void connect(String cmd) {
        try {
            socket = new Socket(SERVER_ADDR, SERVER_PORT);
            OutputStream o = socket.getOutputStream();
            out = new DataOutputStream(o);
            out.writeUTF(cmd);
            out.flush();
            in = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}