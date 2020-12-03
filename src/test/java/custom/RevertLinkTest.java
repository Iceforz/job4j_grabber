package custom;

import org.junit.Test;
import java.util.Iterator;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class RevertLinkTest {

    @Test
    public void whenAddThenIter() {
        RevertLink<Integer> linked = new RevertLink<>();
        linked.add(1);
        linked.add(2);
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenAddAndRevertThenIter() {
        RevertLink<Integer> linked = new RevertLink<>();
        linked.add(1);
        linked.add(2);
        linked.revert();
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(1));
    }
}
