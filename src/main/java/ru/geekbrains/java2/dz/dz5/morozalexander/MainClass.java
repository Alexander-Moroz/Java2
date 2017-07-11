package ru.geekbrains.java2.dz.dz5.morozalexander;

/**
 * Java2. Homework 5
 * Created by СПБ on 29.03.2017.
 *
 * @author Moroz Alexander
 * @version 1.0
 *          <p>
 *          1. Необходимо написать два метода, которые делают следующее:
 *          <p>
 *          Создают одномерный длинный массив, например:
 *          static final int size = 10000000;
 *          static final int h = size / 2;
 *          float[] arr = new float[size];
 *          Заполняют этот массив единицами;
 *          Засекают время выполнения: long a = System.currentTimeMillis();
 *          Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
 *          arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
 *          Проверяется время окончания метода System.currentTimeMillis();
 *          В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a);
 *          Чем отличается первый метод от второго:
 *          <p>
 *          Первый бежит по массиву и высчитывает значения.
 *          <p>
 *          Второй разбивает массив на два массива, в двух потоках высчитывает новые значения, и потом склеивает эти массивы обратно в один.
 *          <p>
 *          Пример деления одного массива на два:
 *          System.arraycopy(arr, 0, a1, 0, h);
 *          System.arraycopy(arr, h, a2, 0, h);
 *          Пример обратной склейки:
 *          System.arraycopy(a1, 0, arr, 0, h);
 *          System.arraycopy(a2, 0, arr, q,h);
 *          По замерам времени:
 *          Для первого метода надо считать время только на цикл расчета:
 *          for (int i = 0; i < size; i++) {
 *          arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
 *          }
 *          Для второго метода замеряете время разбивки массива на 2, просчета каждого из двух массивов и склейки.
 */

public class MainClass {
    private static final int SIZE = 10000000;
    private static final int HALF_SIZE = SIZE / 2;

    public float[] calculate(float[] arr) {
        for (int i = 0; i < arr.length; i++)
            arr[i] = (float) (arr[i] * Math.sin(0.2f + arr[i] / 5) * Math.cos(0.2f + arr[i] / 5) * Math.cos(0.4f + arr[i] / 2));
        return arr;
    }

    public void runOneThread() {
        float[] arr = new float[SIZE];
        for (int i = 0; i < arr.length; i++) arr[i] = 1.0f;
        long a = System.currentTimeMillis();
        calculate(arr);
        System.out.println("One thread method ends with: " + (System.currentTimeMillis() - a));
    }

    public void runTwoThreads() {
        float[] arr = new float[SIZE];
        float[] arr1 = new float[HALF_SIZE];
        float[] arr2 = new float[HALF_SIZE];
        for (int i = 0; i < arr.length; i++) arr[i] = 1.0f;

        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, arr1, 0, HALF_SIZE);
        System.arraycopy(arr, HALF_SIZE, arr2, 0, HALF_SIZE);

        new Thread() {
            public void run() {
                float[] a1 = calculate(arr1);
                System.arraycopy(a1, 0, arr1, 0, a1.length);
            }
        }.start();

        new Thread() {
            public void run() {
                float[] a2 = calculate(arr2);
                System.arraycopy(a2, 0, arr2, 0, a2.length);
            }
        }.start();

        System.arraycopy(arr1, 0, arr, 0, HALF_SIZE);
        System.arraycopy(arr2, 0, arr, HALF_SIZE, HALF_SIZE);
        System.out.println("Two threads ends with: " + (System.currentTimeMillis() - a));
    }

    public static void main(String s[]) {
        MainClass o = new MainClass();
        o.runOneThread();
        o.runTwoThreads();
    }
}