package ru.job4j.isp;

import java.util.ArrayList;
import java.util.List;

public class Tree implements TreeGetBranch {
    private String name;
    private Tree trunk;
    private List<Tree> branches;
    private Action action;

    public Tree(String name, Action action) {
        if (name == null || action == null) {
            throw new IllegalArgumentException("Name and parent cannot be null!");
        }
        this.name = name;
        this.branches = new ArrayList<>();
        this.action = action;
    }

    @Override
    public void getBranch(Tree tree) {
        tree.setTrunk(this);
        this.branches.add(tree);
    }

    public List<Tree> getBranches() {
        return branches;
    }

    public Tree getTrunk() {
        return trunk;
    }

    public String getName() {
        return name;
    }

    public Action getAction() {
        return action;
    }

    public void setBranches(List<Tree> branches) {
        this.branches = branches;
    }

    private void setTrunk(Tree tree) {
        this.trunk = tree;
    }
}

