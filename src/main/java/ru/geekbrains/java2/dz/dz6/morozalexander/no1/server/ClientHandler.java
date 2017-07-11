package ru.geekbrains.java2.dz.dz6.morozalexander.no1.server;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private Socket socket;
    private PrintWriter out;
    private Scanner in;
    //private static int clientsCount = 0;
    private String name;

    //IO settings
    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            out = new PrintWriter(socket.getOutputStream());
            in = new Scanner(socket.getInputStream());
            //clientsCount++;
            //name = "Client #" + clientsCount;
            name = "Client";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            if (in.hasNext()) {
                String w = in.nextLine();
                System.out.println(name + ": " + w);
                out.println("echo: " + w);
                out.flush();
                if (w.equalsIgnoreCase("END")) break;
            }
        }
        try{
            System.out.println("Client disconnected");
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}