package ispmenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Menu {
    private final List<Tree> menu;

    public Menu() {
        this(new ArrayList<>());
    }

    public Menu(List<Tree> menu) {
        this.menu = menu;
    }

    public Menu(Tree... tree) {
        this();
        addTrees(tree);
    }

    public void addTree(Tree tree) {
        menu.add(tree);
    }

    public void addTrees(Tree... trees) {
        menu.addAll(Arrays.asList(trees));
    }

    public void print() {
        printTree("");
    }

    private void printTree(String pref) {
        for (Tree tree : menu) {
            System.out.println(pref + tree);
            new Menu(tree.subItems()).printTree(pref + "--");
        }
    }
}
