package ru.job4j.kiss;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class MaxMinTest {

    @Test
    public void max() {
        MaxMin maxMin = new MaxMin();
        List<String> input = List.of("Slava", "Vitaly", "Kate");
        String rsl = maxMin.max(input, Comparator.naturalOrder());
        String expected = "Kate";
        assertEquals(expected, rsl);
    }

    @Test
    public void min() {
        MaxMin maxMin = new MaxMin();
        List<String> input = List.of("Slava", "Vitaly", "Kate");
        String rsl = maxMin.max(input, Comparator.reverseOrder());
        String expected = "Vitaly";
        assertEquals(expected, rsl);
    }
}