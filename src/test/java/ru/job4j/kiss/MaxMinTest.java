package ru.job4j.kiss;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MaxMinTest {

    private MaxMin maxMin = new MaxMin();
    private List<Person> personList = Arrays.asList(
            new Person("Slava", 34),
            new Person("Vitaly", 23),
            new Person("Kate", 12)
    );

    @Test
    public void whenUseAgeComp() {
        assertThat(maxMin.max(personList, Person.AGECOMP).getAge(), is(34));
        assertThat(maxMin.min(personList, Person.AGECOMP).getAge(), is(12));
    }

    @Test
    public void whenUseLengthComp() {
        assertThat(maxMin.max(personList, Person.LENGTHCOMP).getName(), is("Vitaly"));
        assertThat(maxMin.min(personList, Person.AGECOMP).getName(), is("Kate"));
    }
}