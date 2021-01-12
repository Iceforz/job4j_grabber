package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        T max = value.get(0);
        for (int i = 0; i < value.size() - 1; i++) {
            max = comparator.compare(max, value.get(i + 1)) >= 0  ? max : value.get(i + 1);
        }
        return max;
    }

        public <T> T min(List<T> value, Comparator<T> comparator) {
            T min = value.get(0);
            for (int i = 0; i < value.size() - 1; i++) {
                min = comparator.compare(min, value.get(i + 1)) <= 0  ? min : value.get(i + 1);
            }
            return min;
        }
}