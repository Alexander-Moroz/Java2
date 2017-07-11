package ru.geekbrains.java2.dz.dz6.morozalexander.no1.server;

import java.io.IOException;
import java.net.*;

/**
 * 1. Разобраться с исходным кодом клиентской и серверной части, и для закрепления написать
 *    консольные эхо-сервер и клиент, без подглядывания в методичку;
 *
 * 2. * Написать консольный вариант клиент\серверного приложения, в котором пользователь
 *      может писать сообщения, как на клиентской стороне, так и на серверной.
 *      Т.е. если на клиентской стороне написать "Привет", нажать Enter, то сообщение должно
 *      передаться на сервер и там отпечататься в консоли. Если сделать то же самое на
 *      серверной стороне, сообщение, соответственно, передаётся клиенту и печатается у него
 *      в консоли. Есть одна особенность, которую нужно учитывать: клиент или сервер может
 *      написать несколько сообщений подряд, такую ситуацию необходимо корректно обработать.
 *
 * ВАЖНО! Сервер общается только с одним клиентом, т.е. не нужно запускать цикл, который
 * будет ожидать второго/третьего/... клиентов. (Если будете делать вариант со звездочкой,
 * первую часть дз выполнять НЕ НАДО)
 */

public class Server {
    public static void main(String[] args) {
        ServerSocket server = null;
        Socket socket = null;

        //start server
        try {
            server = new ServerSocket(9000);
            System.out.println("Server created. Waiting for client...");
            //client connection monitoring
            while (true) {
                socket = server.accept();
                System.out.println("Client connected");
                new Thread(new ClientHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                server.close();
                System.out.println("Server closed");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}