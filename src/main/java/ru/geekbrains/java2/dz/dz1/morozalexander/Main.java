package ru.geekbrains.java2.dz.dz1.morozalexander;

import java.util.Scanner;

/**
 * Java2. Homework 1
 * Created by СПБ on 14.03.2017.
 * @author Moroz Alexander
 * @version 1.0
 *
 * ДЗ: Шахматы.
 * Общая постановка задачи:
 *
 * Необходимо написать приложение с использованием консоли,
 * описывающее шахматные фигуры и правила их ходов.
 *
 * Приложение на вход получает название фигуры, начальную и конечную клетки поля.
 *
 * Формат координат латинская буква следом цифра(пример: e2)
 *
 * Описание структуры приложения:
 * Необходимо описать иерархию шахматных фигур и в этой иерархии полиморфно переопределять метод isRightMove(from, to)
 * для каждого типа фигуры.
 *
 * Описание работы приложения:
 * Пользователь вводит название одной фигуры.
 * Вводит начальные и конечные координаты.
 *
 * Результат: Если выбранная фигура может за 1 ход перейти из начальной клетки в конечную,
 * программа выдает на экран положительный ответ, иначе отрицательный. Программа заканчивает работу
 */

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Piece figure = null;
        System.out.println("*******************************************************************************************");
        System.out.println("* ФИГУРЫ: pawn(пешка), knight(конь), rook(ладья), bishop(слон), queen(ферзь), king(король)*");
        System.out.println("* РАЗМЕРЫ ПОЛЯ: a1 - h8                                                                   *");
        System.out.println("*******************************************************************************************");
        System.out.println("ВВЕДИТЕ ФИГУРУ, НАЧАЛЬНУЮ ТОЧКУ, КОНЕЧНУЮ ТОЧКУ. Пример: pawn a1 a2\n");
        System.out.print("> ");

        //СТОЛБЕЦ a = j[0], b = j[1], c = j[2], d = j[3], e = j[4], f = j[5], g = j[6], h = j[7]
        //СТРОКА 1 => i[max] - 0, 2 => i[max] - 1, 3 => i[max] - 2, 4 => i[max] - 3, 5 => i[max] - 4, 6 => i[max] - 5, 7 => i[max] - 6, 8 => i[max] - 7
        String piece = scanner.next();
        String startPoint = scanner.next();
        String endPoint = scanner.next();

        switch (piece) {
            case "pawn" : {
                figure = new Pawn();
                break;
            }
            case "knight" : {
                figure = new Knight();
                break;
            }
            case "rook" : {
                figure = new Rook();
                break;
            }
            case "bishop" : {
                figure = new Bishop();
                break;
            }
            case "queen" : {
                figure = new Queen();
                break;
            }
            case "king" : {
                figure = new King();
                break;
            }
            default : {
                System.out.println("ERROR");
                System.exit(0);
            }
        }

        System.out.println(figure.isRightMove(startPoint, endPoint));
    }
}