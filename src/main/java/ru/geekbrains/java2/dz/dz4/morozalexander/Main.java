package ru.geekbrains.java2.dz.dz4.morozalexander;

import java.io.IOException;

/**
 * Created by СПБ on 25.03.2017.
 *
 * 1. Создать окно для клиентской части чата (
 *      большое текстовое поле для отображения переписки,
 *      однострочное текстовое поле для ввода сообщений,
 *      кнопка для отсылки сообщений
 *    ).
 *
 *    Сообщение должно отсылаться либо по нажатию кнопки на форме, либо по нажатию кнопки Enter.
 *    "Отправленное" сообщение должно появиться в большом текстовом поле, и быть продублировано в текстовый файл.
 *
 * 2. Для записи в файл можно использовать PrintWriter pw = new PrintWriter(new FileWriter("1.txt")); pw.println(...);
 *
 */

public class Main {
    public static void main(String[] args) throws IOException {
        new Chat();
    }
}