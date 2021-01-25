package ru.job4j.srp;

import java.util.function.Predicate;

public interface Rep {
    String generate(Predicate<Employee> filter);
}
