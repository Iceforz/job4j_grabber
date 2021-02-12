package ispmenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreeMenu implements Tree {
    private final String treeName;

    private final Action action;

    private final List<Tree> branches = new ArrayList<>();

    public TreeMenu(String treeName, Action action) {
        this(treeName, action, new Tree[]{});
    }

    public TreeMenu(String treeName, Action action, Tree... trees) {
        this.treeName = treeName;
        this.action = action;
        add(trees);
    }

    private void add(Tree... trees) {
        branches.addAll(Arrays.asList(trees));
    }

    @Override
    public String name() {
        return treeName;
    }

    @Override
    public void action() {
        action.makeAction();
    }

    @Override
    public List<Tree> subItems() {
        return branches;
    }

    @Override
    public String toString() {
        return treeName;
    }
}

