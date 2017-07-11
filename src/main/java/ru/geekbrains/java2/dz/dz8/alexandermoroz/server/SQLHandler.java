package ru.geekbrains.java2.dz.dz8.alexandermoroz.server;

import java.sql.*;

public class SQLHandler {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "12345678";
    private static final String SQL_SELECT = "SELECT nickname FROM main WHERE login = ? AND password = ?;";
    private static final String SQL_INSERT = "INSERT INTO main VALUES (password, nickname, login) VALUES (?, ?, ?);";
    private static final String SQL_UPDATE = "UPDATE main SET password = ? WHERE login = ?;";
    private static final String SQL_UPDATE_NICK = "UPDATE main SET nickname = ? WHERE nickname = ?;";
    private static final String SQL_SELECT_ALL = "SELECT * FROM main WHERE login = ?;";

    private static Connection connection;
    private static PreparedStatement preparedStatement;

    public static void connect() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println("Connection Error");
        }
    }

    public static String getNickByLoginPassword(String login, String password) {
        String w = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) w = resultSet.getString("nickname");
        } catch (SQLException e) {
            System.out.println("SQL Query Error");
        }
        return w;
    }

    public static String getPasswordByLogin(String login) {
        String w = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) w = resultSet.getString("password");
        } catch (SQLException e) {
            System.out.println("SQL Query Error");
        }
        return w;
    }

    public static String getLoginByLogin(String login) {
        String w = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) w = resultSet.getString("login");
        } catch (SQLException e) {
            System.out.println("SQL Query Error");
        }
        return w;
    }

    public static String addNewUser(String password, String nickname, String login) {
        String w = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, nickname);
            preparedStatement.setString(3, login);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("SQL Query Error");
        }
        return w;
    }

    public static String changePassword(String login, String password) {
        String w = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("SQL Query Error");
        }
        return w;
    }

    public static String changeNick(String oldNick, String newNick) {
        String w = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_NICK);
            preparedStatement.setString(1, newNick);
            preparedStatement.setString(2, oldNick);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("SQL Query Error");
        }
        return w;
    }
}