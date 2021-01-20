package ru.job4j.gc;

public class Account {
    private final String name;
    private final int age;

    public Account(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    protected void finalize() {
        System.out.printf("Account deleted %s %d%n", name, age);
    }

    public static void main(String[] args) throws InterruptedException {
        Runtime rt = Runtime.getRuntime();
        long start = rt.freeMemory();
        System.out.println("Memory before object was created = " + start + " byte");
        for (int i = 0; i < 15; i++) {
            new Account("Max" + i, i + 10);
        }
        long end = rt.freeMemory();
        System.out.println("Memory after 15 objects was created = " + end + " byte");
        long resultAll = start - end;
        System.out.printf("Amount of memory that was used for 15 objects of Account = "
                + resultAll + " byte%n"
                + "Amount of memory on one Account = " + resultAll / 15 + " byte %n");
        long secondStart = rt.freeMemory();
        System.out.println("Before = " + secondStart + " byte");
          new EmptyAccount();
        long secondEnd = rt.freeMemory();
        System.out.println("After object without fields was created = " + secondEnd + " byte");
        long secondResult = secondStart - secondEnd;
        System.out.println("Memory used by object without fields = " + secondResult + " byte");
        System.gc();
        Thread.sleep(4000);
    }
}

