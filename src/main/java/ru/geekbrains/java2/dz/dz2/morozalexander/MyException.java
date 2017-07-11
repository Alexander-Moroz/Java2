package ru.geekbrains.java2.dz.dz2.morozalexander;

public class MyException extends RuntimeException {

    private String message;

    public MyException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ru.geekbrains.java2.dz.dz2.morozalexander.MyException: " + message;
    }
}