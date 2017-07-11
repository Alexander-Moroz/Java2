package ru.geekbrains.java2.dz.dz3.morozalexander;

/**
 * Created by СПБ on 22.03.2017.
 */
public class Passenger {
    private String name;
    private String passport;
    private int flightNumber;

    Passenger(String name, String passport, int flightNumber) {
        this.name = name;
        this.passport = passport;
        this.flightNumber = flightNumber;
    }

    void setName(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    void setPassport(String passport) {
        this.passport = passport;
    }

    String getPassport() {
        return passport;
    }

    void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    int getFlightNumber() {
        return flightNumber;
    }

    @Override
    public String toString() {
        return getName() + " " + getPassport();
    }
}
