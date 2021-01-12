package template;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

 class GeneratorTest {

    @Test
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
    public void whenNotEnough() {
        Generator generator = new TGenerator();
        String temp = "Hello ${name}, how you ${subject}?";
        Map<String, String> map = Map.of("name", "Alex");
        generator.produce(temp, map);
    }

    @Test
    public void whenTooMany() {
        Generator generator = new TGenerator();
        String temp = "Hello ${name}, how you ${subject}?";
        Map<String, String> map = Map.of(
                "name", "Alex",
                "subject", "doing",
                "anotherSubject", "buddy"
        );
        generator.produce(temp, map);
    }
}