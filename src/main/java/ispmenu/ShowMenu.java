package ispmenu;

public class ShowMenu {
    private Action action = new ActionAction();

    private Tree tree1 = new TreeMenu("tree1.1.1", action);
    private Tree tree2 = new TreeMenu("tree1.1.2", action);
    private Tree tree11 = new TreeMenu("tree1.1", action, tree1, tree2);
    private Tree tree12 = new TreeMenu("tree1.2", action);
    private Tree tree111 = new TreeMenu("tree1", action, tree11, tree12);
    private Tree tree112 = new TreeMenu("tree2", action);

    private Menu menu = new Menu(tree111);

    public static void main(String[] args) {
        ShowMenu showMenu = new ShowMenu();
        showMenu.menu.addTree(showMenu.tree112);
        showMenu.menu.print();
    }
}

