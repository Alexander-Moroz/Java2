package ru.geekbrains.java2.dz.dz8.alexandermoroz.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

public class ClientHandler implements Runnable {
    private MyServer owner;
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private String name;
    private int authTimer;

    public int getAuthTimer() {
        return authTimer;
    }

    public void setAuthTimer(int authTimer) {
        this.authTimer = authTimer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClientHandler(Socket socket, MyServer owner) {
        try {
            this.owner = owner;
            this.socket = socket;
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            name = "";
            authTimer = 0;
        } catch (IOException e) {
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                String w = in.readUTF();
                if (w != null) {
                    System.out.println(w);
                    String[] n = w.split("\t");
                    System.out.println(Arrays.toString(n));
                    if (n[0].equalsIgnoreCase("addNewUser")) {
                        SQLHandler.addNewUser(n[1], n[2], n[3]);
                        continue;
                    }
                    if (n[0].equals("changePassword")) {
                        SQLHandler.changePassword(n[1], n[2]);
                        continue;
                    }
                    if (n[0].equals("auth")) {
                        String nick = SQLHandler.getNickByLoginPassword(n[1], n[2]);
                        String login = SQLHandler.getLoginByLogin(n[1]);
                        String password = SQLHandler.getPasswordByLogin(n[1]);
                        if (nick != null && !owner.isNicknameUsed(nick)) {
                            owner.broadcastMsg(nick + " connected to the chatroom");
                            name = nick;
                            sendMsg("zxcvb");
                            break;
                        } else {
                            if (nick == null) {
                                sendMsg("Auth Error: No such account");
                            } else if (login.equals(n[1]) && !password.equals(n[2])) {
                                sendMsg("Auth Error: Incorrect Password");
                                //break;
                            } else if (owner.isNicknameUsed(nick)) {
                                sendMsg("Auth Error: Account is busy");
                            }
                        }
                    }
                }
                Thread.sleep(100);
            }

            while (true) {
                String msg = in.readUTF();
                if (msg != null) {
                    if (msg.contains("/changenick ")) {
                        String[] newNick = msg.split("/changenick ");
                        if (newNick.length > 0) {
                            owner.changeNick(name, newNick[1]);
                            //SQLHandler.changeNick(name, newNick[1]);
                        }
                    } else {
                        String[] s = msg.split("@#");
                        if (s.length == 2) {
                            owner.broadcastMsgByUser(s[0], name + ": " + s[1]);
                        } else {
                            owner.broadcastMsg(name + ": " + msg);
                            System.out.println(name + ": " + msg);
                            if (msg.equalsIgnoreCase("END")) break;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("IOException");
        } catch (InterruptedException e) {
            System.out.println("Thread sleep error");
        } finally {
            close();
        }
    }

    public void close() {
        try {
            System.out.println("Client disconnected");
            owner.remove(this);
            socket.close();
            if (!name.isEmpty()) owner.broadcastMsg(name + " disconnected from the chatroom");
        } catch (IOException e) {
        }
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
            out.flush();
        } catch (IOException e) {
        }
    }
}