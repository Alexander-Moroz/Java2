package ru.geekbrains.java2.dz.dz1.morozalexander;

/**
 * Created by СПБ on 14.03.2017.
 */

public class Pawn extends Piece {

    @Override
    boolean isRightMove(String from, String to) {
        System.out.print("pawn move from ");

        //beginPoint
        String litera1 = from.substring(0, 1);
        int index1 = Integer.parseInt(from.substring(1, from.length()));

        if (abc.contains(litera1) && (index1 > 0 && index1 < 9)) {
            a1 = 8 - index1;
            a2 = abc.indexOf(litera1);
        } else {
            System.out.println("ERROR");
            System.exit(0);
        }

        //to
        String litera2 = to.substring(0, 1);
        int index2 = Integer.parseInt(to.substring(1, to.length()));

        if (abc.contains(litera1) && (index2 > 0 && index2 < 9)) {
            b1 = 8 - index2;
            b2 = abc.indexOf(litera2);
        } else {
            System.out.println("ERROR");
            System.exit(0);
        }

        //check next...
        System.out.println(from + " = A(" + a1 + ", " + a2 + ") to " + to + " = B(" + b1 + ", " + b2 + ")\n");

        printSteps();
        System.out.println();

        // 1-2 UP STEPS CHECK
        if ((a2 == b2) && (((a1 - b1) < 3) && ((a1 - b1) > 0))) return true;
        // 1-2 DOWN STEPS CHECK
        if ((a2 == b2) && (((b1 - a1) < 3) && ((b1 - a1) > 0))) return true;

        return false;
    }
}