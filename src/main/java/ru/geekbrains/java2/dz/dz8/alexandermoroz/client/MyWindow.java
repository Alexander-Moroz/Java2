package ru.geekbrains.java2.dz.dz8.alexandermoroz.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MyWindow extends JFrame {
    JTextField jtf;
    JTextArea jta;

    final String SERVER_ADDR = "localhost";
    final int SERVER_PORT = 1111;
    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    boolean authorized = false;

    public MyWindow() {
        super("Client");
        setBounds(600, 300, 500,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jta = new JTextArea();
        jta.setEditable(false);;
        jta.setLineWrap(true);
        JScrollPane jsp = new JScrollPane(jta);
        add(jsp, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        JPanel authPanel = new JPanel(new GridLayout());

        JTextField jtfLogin = new JTextField();
        JTextField jtfPassword = new JTextField();
        JButton jbAuth = new JButton("Auth");

        authPanel.add(jtfLogin);
        authPanel.add(jtfPassword);
        authPanel.add(jbAuth);
        jtfLogin.setToolTipText("LOGIN");
        add(authPanel, BorderLayout.NORTH);

        jbAuth.addActionListener(e -> {
            if (socket == null || socket.isConnected()) {
                System.out.println("client TRY");
                connectToServer();
            }
            sendAuthCmd("auth\t" + jtfLogin.getText() + "\t" + jtfPassword.getText());
        });

        add(bottomPanel, BorderLayout.SOUTH);
        JButton jbSend = new JButton("SEND");
        bottomPanel.add(jbSend, BorderLayout.EAST);
        jtf = new JTextField();
        bottomPanel.add(jtf, BorderLayout.CENTER);

        jbSend.addActionListener(e -> {
            if (!jtf.getText().trim().isEmpty()) {
                sendMsg();
                jtf.grabFocus();
            }
        });

        jtf.addActionListener(e -> sendMsg());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                try {
                    out.writeUTF("end");
                    out.flush();
                    out.close();
                    in.close();
                } catch (IOException e1) {
                } finally {
                    try {
                        socket.close();
                    } catch (IOException e2) {
                    }
                }
            }
        });

        JMenuBar mainMenu = new JMenuBar();
        JMenu menuActions = new JMenu("Actions");
        JMenu menuAbout = new JMenu("About");
        JMenuItem menuAboutMessenger = new JMenuItem("About Messenger");
        JMenuItem menuAddNewUser = new JMenuItem("Add New User");
        JMenuItem menuChangePassword = new JMenuItem("Change Password");
        JMenuItem menuFileSave = new JMenuItem("Save");
        JMenuItem menuFileLoad = new JMenuItem("Load");
        JMenuItem menuFileExit = new JMenuItem("Exit");

        setJMenuBar(mainMenu);
        mainMenu.add(menuActions);
        mainMenu.add(menuAbout);
        menuActions.add(menuAddNewUser);
        menuActions.add(menuChangePassword);
        menuActions.add(menuFileSave);
        menuActions.add(menuFileLoad);
        menuActions.addSeparator();
        menuActions.add(menuFileExit);
        menuAbout.add(menuAboutMessenger);

        menuAddNewUser.addActionListener(e -> {
            AddNewUserWindow about = new AddNewUserWindow();
            about.setVisible(true);
        });

        menuChangePassword.addActionListener(e -> {
            ChangePasswordWindow about = new ChangePasswordWindow();
            about.setVisible(true);
        });

        menuAboutMessenger.addActionListener(e -> {
            AboutWindow about = new AboutWindow();
            about.setVisible(true);
        });

        menuFileSave.addActionListener(e -> {
            JFileChooser fileopen = new JFileChooser();
            int ret = fileopen.showDialog(null, "Save file");
            if (ret == JFileChooser.APPROVE_OPTION) {
                File file = fileopen.getSelectedFile();
                String data = jta.getText();
                saveData(file, data);
            }
        });

        menuFileLoad.addActionListener(e -> {
            JFileChooser fileopen = new JFileChooser();
            int ret = fileopen.showDialog(null, "Load file");
            if (ret == JFileChooser.APPROVE_OPTION) {
                File file = fileopen.getSelectedFile();
                jta.setText(loadData(file));
            }
        });

        menuFileExit.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    public void connectToServer() {
        try {
            socket = new Socket(SERVER_ADDR, SERVER_PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        String w = in.readUTF();
                        if (w != null) {
                            if (w.equals("zxcvb")) {
                                jta.setText("Connected\n");
                                authorized = true;
                                break;
                            } else {
                                jta.append(w);
                                jta.append("\n");
                            }
                        }
                    }

                    while (true) {
                        String w = in.readUTF();
                        if (w != null) {
                            if (w.equalsIgnoreCase("end session")) break;
                            jta.append(w);
                            jta.append("\n");
                            jta.setCaretPosition(jta.getDocument().getLength());
                        }
                        Thread.sleep(100);
                    }
                } catch (Exception e) {
                    authorized = false;
                    System.out.println("client ERR");
                }
            }
        }).start();
    }

    public void sendAuthCmd(String str) {
        try {
            out.writeUTF(str);
            out.flush();
        } catch (IOException e) {
            System.out.println("Send auth error");
        }
    }

    public void sendMsg() {
        if (authorized) {
            try {
                String a = jtf.getText();
                out.writeUTF(a);
                out.flush();
                jtf.setText("");
            } catch (IOException e) {
                System.out.println("Send msg error");
            }
        }
    }

    public void saveData(File filename, String data) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(data);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String loadData(File filename) {
        StringBuilder response = new StringBuilder("");
        try (Scanner scanner = new Scanner(filename)) {
            while (scanner.hasNext()) {
                String str = scanner.nextLine();
                response.append(str).append("\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return response.toString();
    }
}