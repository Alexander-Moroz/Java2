package ru.geekbrains.java2.dz.dz7.morozalexander;

/**
 * Created by СПБ on 04.04.2017.
 * @author Alexander Moroz
 *
 * в классе DataBase реализовать два метода:
 * реализовать добавление пользователей.
 * реализовать измнение пароля по логину.
 */
public class Main {
    public static void main(String[] args) {
        DataBase dataBase = new DataBase();
        dataBase.getConnectionToDB(DataBase.DB_URL, DataBase.USER, DataBase.PASSWORD, "log3");
    }
}