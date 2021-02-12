package ispmenu;

import java.util.List;

public interface Tree {
    String name();

    void action();

    List<Tree> subItems();
}

