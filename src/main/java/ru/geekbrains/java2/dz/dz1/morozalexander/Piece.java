package ru.geekbrains.java2.dz.dz1.morozalexander;

/**
 * Created by СПБ on 14.03.2017.
 */

public abstract class Piece {
    String abc = "abcdefgh";
    int a1, a2, b1, b2;
    int max = 8;
    String[][] chessField = new String[max][max];
    abstract boolean isRightMove(String from, String to);

    void printSteps(){
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                if (i == a1 && j == a2) chessField[i][j] = "[A]";
                else if (i == b1 && j == b2) chessField[i][j] = "[B]";
                else if (((i % 2 == 0) && (j % 2 == 0)) || ((i % 2 != 0) && (j % 2 != 0))) chessField[i][j] = "[ ]";
                else chessField[i][j] = "[■]";
                System.out.print(chessField[i][j]);
            }
            System.out.println();
        }
    }

}