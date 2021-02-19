package ru.job4j.isp;

public interface MenuGetTreeAction {
    Action getTreeAction(String treeName);

    Action getTreeAction(Tree tree);
}
