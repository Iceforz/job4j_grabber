package ru.job4j.gc;

public class GCDemo {
    private static final long KB = 1000;
    private static final long MB = KB * KB;
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    public static void info() {
        final long freeMemory = ENVIRONMENT.freeMemory();
        final long totalMemory = ENVIRONMENT.totalMemory();
        final long maxMemory = ENVIRONMENT.maxMemory();
        System.out.println("=== Environment state ===");
        System.out.printf("Free: %d%n", freeMemory / MB);
        System.out.printf("Total: %d%n", totalMemory / MB);
        System.out.printf("Max: %d%n", maxMemory / MB);
    }

    public static void main(String[] args) {
        info();
        for (int i = 0; i < 10000; i++) {
            new Person(i, "N" + i);
        }
        System.gc();
        info();
    }
}

/* SerialGC -XX:+UseSerialGC
Удалил немного объектов
Отработал за [0.008s]
[0.114s][info   ][gc     ] GC(0) Pause Young (Allocation Failure) 3M->2M(5M) 3.548ms
[0.117s][info   ][gc     ] GC(1) Pause Full (Allocation Failure) 2M->2M(6M) 2.978ms
[0.124s][info   ][gc     ] GC(2) Pause Full (System.gc()) 2M->2M(7M) 3.975ms


 ParallelGC -XX:+UseParallelGC
 Удалил больше всех объектов
 Отработал за [0.009s]
 [0.108s][info   ][gc     ] GC(0) Pause Young (Allocation Failure) 2M->1M(4M) 1.644ms
[0.111s][info   ][gc     ] GC(1) Pause Full (Ergonomics) 1M->1M(6M) 3.707ms
[0.123s][info   ][gc     ] GC(2) Pause Young (System.gc()) 2M->2M(6M) 2.346ms
[0.132s][info   ][gc     ] GC(3) Pause Full (System.gc()) 2M->2M(6M) 8.158ms


CMS GC -XX:+UseConcMarkSweepGC
Java HotSpot(TM) 64-Bit Server VM warning: Ignoring option UseConcMarkSweepGC; support was removed in 14.0
[0.009s][info][gc] Using G1
[0.025s][info][gc] Periodic GC disabled
[0.119s][info][gc] GC(0) Pause Full (System.gc()) 3M->2M(5M) 7.438ms


G1 GC -XX:+UseG1GC.
Удалил пару объектов
[0.122s][info][gc] GC(0) Pause Full (System.gc()) 3M->2M(5M) 6.362ms
 */