package ru.job4j.isp;

import java.util.*;

public class Menu implements MenuGetTree, MenuGetTreeAction, MenuShow {
    private final List<Tree> rootTrees = new ArrayList<>();
    private final Set<String> treeNames = new HashSet<>();

    @Override
    public boolean addTree(Tree tree, String trunkName) {
        if (tree == null) {
            return false;
        }
        if (trunkName == null) {
            addTree(tree);
        }
        if (!treeNames.contains(trunkName)) {
            return false;
        }
        Tree trunk = findTreeByName(trunkName);
        if (trunk != null) {
            trunk.getBranch(tree);
            treeNames.add(tree.getName());
            return true;
        }
        return false;
    }

    @Override
    public boolean addTree(Tree tree, Tree trunk) {
        if (trunk != null) {
            return this.addTree(tree, trunk.getName());
        }
        return false;
    }

    @Override
    public boolean addTree(Tree tree) {
        if (treeNames.contains(tree.getName())) {
            return false;
        }
        treeNames.add(tree.getName());
        return rootTrees.add(tree);
    }

    @Override
    public Action getTreeAction(String treeName) {
        Tree tree = findTreeByName(treeName);
        return tree == null ? null : tree.getAction();
    }

    @Override
    public Action getTreeAction(Tree tree) {
        if (tree != null) {
            return this.getTreeAction(tree.getName());
        }
        return null;
    }

    @Override
    public void show() {
        Map<String, String> treeStructure = new HashMap<>();
        rootTrees.forEach(i -> treeStructure.put(i.getName(), "┝"));
        LinkedList<Tree> stack = new LinkedList<>(rootTrees);
        while (!stack.isEmpty()) {
            Tree tree = stack.pop();
            String name = tree.getName();
            System.out.println(treeStructure.get(name) + name);
            List<Tree> children = tree.getBranches();
            if (children != null) {
                children.forEach(
                        i -> treeStructure.put(
                                i.getName(),
                                treeStructure.get(name) + strFromChar('╺', name.length() / 2)));
                stack.addAll(0, tree.getBranches());
            }
        }
    }

    public List<Tree> getTrees() {
        return rootTrees;
    }

    private Tree findTreeByName(String name) {
        LinkedList<Tree> stack = new LinkedList<>(rootTrees);
        while (!stack.isEmpty()) {
            Tree tree = stack.pop();
            if (name.equals(tree.getName())) {
                return tree;
            }
            if (tree.getBranches() != null) {
                stack.addAll(tree.getBranches());
            }
        }
        return null;
    }

    private String strFromChar(char symbol, int number) {
        char[] repeat = new char[number];
        Arrays.fill(repeat, symbol);
        return new String(repeat);
    }
}
