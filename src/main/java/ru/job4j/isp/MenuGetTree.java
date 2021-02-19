package ru.job4j.isp;

public interface MenuGetTree {
    boolean addTree(Tree tree, String trunkName);

    boolean addTree(Tree tree, Tree trunk);

    boolean addTree(Tree tree);
}
