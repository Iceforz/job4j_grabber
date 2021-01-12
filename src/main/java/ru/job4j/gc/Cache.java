package ru.job4j.gc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Cache {
    private final Map<String, SoftReference<List<String>>> softCache;
    private final String directory;

    public Cache(final String directory) {
        this.directory = directory;
        this.softCache = new HashMap<>();
    }

    public List<String> read(String file) {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader(directory + "/" + file))) {
            rsl = bufferedReader.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public List<String> add(String file) {
        List<String> rsl = read(file);
        if (rsl != null) {
            SoftReference<List<String>> softReference = new SoftReference<>(rsl);
            softCache.put(file, softReference);
        }
        return rsl;
    }

    public List<String> get(String key) {
        List<String> rsl;
        if (softCache.containsKey(key)) {
            rsl = softCache.get(key).get();
            if (rsl == null) {
                rsl = add(key);
            }
        } else {
            rsl = add(key);
        }
        return rsl;
    }
}

