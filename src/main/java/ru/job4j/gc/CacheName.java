package ru.job4j.gc;

import java.io.IOException;

public class CacheName {
    public static void main(String[] args) throws IOException {
        Cache cache = new Cache("./src/main/files");
        System.out.println(cache.get("company.txt"));
        System.out.println(cache.get("users.txt"));
    }
}
