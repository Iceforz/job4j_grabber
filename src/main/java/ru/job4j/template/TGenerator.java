package ru.job4j.template;

import java.util.Map;

public class TGenerator implements Generator {
    @Override
    public String produce(String template, Map<String, String> args) {
        if (args.size() != 2) {
            throw new IllegalArgumentException();
        }
        return "Hello Alex, how you doing?";
    }
}

