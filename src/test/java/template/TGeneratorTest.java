package template;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TGeneratorTest {

    @Test
    public void whenWork() {
        TGenerator generator = new TGenerator();
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
        TGenerator generator = new TGenerator();
        String temp = "Hello ${name}, how you ${subject}?";
        Map<String, String> map = Map.of("name", "Alex");
        assertThrows(IllegalArgumentException.class, () -> generator.produce(temp, map));
    }

    @Test
    public void whenToMany() {
        TGenerator generator = new TGenerator();
        String temp = "Hello ${name}, how you ${subject}?";
        Map<String, String> map = Map.of(
                "name", "Alex",
                "subject", "doing",
                "anotherSubject", "buddy"
        );
        assertThrows(IllegalArgumentException.class, () -> generator.produce(temp, map));
    }
}
