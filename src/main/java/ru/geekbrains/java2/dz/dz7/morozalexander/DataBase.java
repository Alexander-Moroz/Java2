package ru.geekbrains.java2.dz.dz7.morozalexander;

import java.sql.*;

/**
 * Created by СПБ on 04.04.2017.
 *
 * added 3 new methods:
 * deleteRow(Statement statement, String login)
 * insertRow(Statement statement, String login, String nickname, String password)
 * updatePasswordForLogin(Statement statement, String login, String password)
 */

public class DataBase {
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    public static final String USER = "postgres", PASSWORD = "12345678";
    final String SQL_SELECT_ALL = "SELECT * FROM main";
    final String SQL_SELECT = "SELECT * FROM main WHERE login = ?";

    public void getConnectionToDB(String url, String user, String password, String login) {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            if (connection != null) {
                System.out.println("connection to db");
                System.out.println("Подключение к БД успешно\n");
                Statement statement = connection.createStatement();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT);
                if (login != null && !login.isEmpty()) {
                    findLogin(preparedStatement, login);
                } else {
                    showFullTableColumnNumber(statement);
                }

                //SQL DELETE
                deleteRow(statement, "login001");
                //SQL INSERT
                insertRow(statement, "login001", "nickname001", "password1");
                //SQL UPDATE
                updatePasswordForLogin(statement, "log1", "SET_NEW_PASSWORD_HERE");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRow(Statement statement, String login) throws SQLException {
        String delete = "DELETE FROM main WHERE login = '" + login + "'";
        statement.executeUpdate(delete);
        System.out.println("FULL DATA: ");
        showFullTable(statement);
    }

    public void insertRow(Statement statement, String login, String nickname, String password) {
        String insert = "INSERT INTO main (login, nickname, password) VALUES ('" + login + "', '" + nickname + "', '" + password + "')";
        //System.out.println(insert);
        try {
            statement.executeUpdate(insert);
            System.out.println("FULL DATA:");
            showFullTable(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePasswordForLogin(Statement statement, String login, String password) {
        String update = "UPDATE main SET password = '" + password + "' WHERE login = '" + login + "'";
        //System.out.println(update);
        try {
            statement.executeUpdate(update);
            System.out.println("FULL DATA:");
            showFullTable(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void findLogin(PreparedStatement preparedStatement, String queryLogin) {
        try {
            preparedStatement.setString(1, queryLogin);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String login = resultSet.getString("login");
                String nickname = resultSet.getString("nickname");
                String password = resultSet.getString("password");
                System.out.println(login + " | " + nickname + " | " + password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void findLogin(Statement statement, String queryLogin) {
        try {
            String sql = "SELECT * FROM main WHERE login = '" + queryLogin + "'";
            System.out.println(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String login = resultSet.getString("login");
                String nickname = resultSet.getString("nickname");
                String password = resultSet.getString("password");
                System.out.println(login + " = " + nickname + " = " + password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showFullTableColumnNumber(Statement statement) {
        try {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                String login = resultSet.getString(1);
                String nickname = resultSet.getString(2);
                String password = resultSet.getString(3);
                System.out.println(login + " - " + nickname + " - " + password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showFullTable(Statement statement) {
        try {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                String login = resultSet.getString("login");
                String nickname = resultSet.getString("nickname");
                String password = resultSet.getString("password");
                System.out.println(login + ", " + nickname + ", " + password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
