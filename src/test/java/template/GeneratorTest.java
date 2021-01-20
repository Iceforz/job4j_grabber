package template;


//import org.junit.jupiter.api.Test;
import java.lang.IllegalArgumentException;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GeneratorTest {

    @Test
    @Ignore
    public void whenWork() {
        Generator generator = new TGenerator();
        String temp = "Hello ${name}, how you ${subject}?";
        Map<String, String> map = Map.of(
                "name", "Alex",
                "subject", "doing"
        );
        assertThat(
                generator.produce(temp, map), is("Hello Alex, how you doing?")
        );
    }

    @Test
    @Ignore
    public void whenNotEnough() {
        Generator generator = new TGenerator();
        String temp = "Hello ${name}, how you ${subject}?";
        Map<String, String> map = Map.of("name", "Alex");
        assertThrows(IllegalArgumentException.class, () ->
                generator.produce(temp, map));
    }

    @Test
    @Ignore
    public void whenTooMany() {
        Generator generator = new TGenerator();
        String temp = "Hello ${name}, how you ${subject}?";
        Map<String, String> map = Map.of(
                "name", "Alex",
                "subject", "doing",
                "anotherSubject", "buddy"
        );
        assertThrows(IllegalArgumentException.class, () ->
                generator.produce(temp, map));
    }
}