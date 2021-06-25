package ru.job4j.math;


import static ru.job4j.math.MathFunction.*;

public class MathCalculator {
    public static double sumAndMultiply(double first, double second) {
        return sum(first, second)
               + multiply(first, second);
    }

    public static double divAndMinus(double first, double second) {
        return div(first, second)
                + minus(first, second);
    }

    public static double everyThing(double first, double second) {
        return sum(first, second)
               + multiply(first, second)
               + div(first, second)
                + minus(first, second);
    }

    public static void main(String[] args) {
        System.out.println("Результат расчета равен: " + sumAndMultiply(10, 20));
        System.out.println("Результат расчета равен: " + divAndMinus(10, 20));
        System.out.println("Результат расчета равен: " + everyThing(10, 20));
    }
}
