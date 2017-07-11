package ru.geekbrains.java2.dz.dz3.morozalexander;

import java.util.*;

/**
 * Java2. Homework 3
 * Created by СПБ on 22.03.2017.
 * @author Moroz Alexander
 * @version 1.0
 *
 * Задание
 * Нужно реализовать рассадку пассажирова по рейсам. Каждый пассажир должен сесть на свой рейс.
 * Самолет может иметь или не иметь пассажирова.
 *
 * Программа должна позволять запрашивать у пользователя ввод пассажиров, для каждого пассажира вводится:
 *      имя;
 *      номер документа, удовлетворяющего личность;
 *      номер рейса;
 *
 * По окончанию ввода пассажиров в консоль выводится список всех рейсов и отправляющихся на нем пассажиров (имя и номер документа).
 * Рейсы должны быть упорядочены по номеру, пассажиры - по имени.
 * Для каждого рейса указывается количество пассажиров на нем.
 * Ввод данных производится по пассажирам.
 *
 * Указания
 * При решении задачи нужно использовать интерфейсы List (для хранения пассажировов в рейсе) и Map (для сопоставления имени рейса и пассажиров в рейсе).
 *
 * Предположим, что вы реализовали класс Passenger, тогда объявления списка пассажиров следует делать следующим образом:
 * List<Passenger> passengers = new ArrayList<Passenger>();
 *
 * Для ввода данных имеет смысл испльзовать класс Scanner следующим образом:
 * Scanner scanner = new Scanner(System.in);
 * int flightNumber = scanner.nextInt();
 *
 * При решение задачи должны быть использована одна (и только одна) TreeMap.
 *
 * Класс Passenger должен содержать только private-поля и методы доступа к нему (set/get).
 *
 * Используйте generics при объявление переменных с коллекциями:
 * Map<Integer,List<Passenger>> passengers = new TreeMap<Integer,List<Passenger>>
 *
 * Для перебора элементов в коллекциях используйте for-each вариант for:
 * Collection<Passenger> = new ArrayList<Passenger>();
 * for (Passenger passenger : passengers) { }
 */

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<Integer, List<Passenger>> passengersMap = new TreeMap<Integer, List<Passenger>>();

        //List of flights
        List<Passenger> flight1 = new ArrayList<>();
        List<Passenger> flight2 = new ArrayList<>();
        List<Passenger> flight3 = new ArrayList<>();
        List<Passenger> flight4 = new ArrayList<>();

        System.out.println("Valid flight numbers: 1, 2, 3, 4\n");

        //Registration
        for (int i = 1; i < 4; i++) {
            System.out.println("Register passenger №" + i);
            System.out.println("enter: name passport flightNumber");
            String name = sc.next();
            String passport = sc.next();
            int flightNumber = sc.nextInt();
            switch (flightNumber) {
                case 1 : flight1.add(new Passenger(name, passport, flightNumber)); break;
                case 2 : flight2.add(new Passenger(name, passport, flightNumber)); break;
                case 3 : flight3.add(new Passenger(name, passport, flightNumber)); break;
                case 4 : flight4.add(new Passenger(name, passport, flightNumber)); break;
                default : System.out.println("invalid flight number");
            }
        }

        //Sort by name
        Comparator<Passenger> c = (name1, name2) -> name1.getName().compareToIgnoreCase(name2.getName());
        flight1.sort(c);
        flight2.sort(c);
        flight3.sort(c);
        flight4.sort(c);

        //Add to map
        passengersMap.put(1, flight1);
        passengersMap.put(2, flight2);
        passengersMap.put(3, flight3);
        passengersMap.put(4, flight4);

        //View map
        for (Map.Entry e : passengersMap.entrySet()) {
            System.out.println("FLIGHT NUMBER " + e.getKey() + " passengers:");
            for (Passenger pp : (ArrayList<Passenger>)e.getValue()) {
                System.out.println("  " + pp.getName() + " " + pp.getPassport());
            }
            System.out.println("Total passengers count: " + ((ArrayList<Passenger>) e.getValue()).size() + "\n");
        }
    }

}