package ru.job4j.isp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MenuTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Test
    public void whenAddRootTree() {
        Tree tree = new Tree("Tree", new TestAction());
        Menu menu = new Menu();
        menu.addTree(tree);
        List<Tree> expected = List.of(tree);
        assertThat(menu.getTrees(), is(expected));
    }

    @Test
    public void whenAddRootItemAndSubItem() {
        Tree tree1 = new Tree("Tree1", new TestAction());
        Tree tree2 = new Tree("Tree1.1", new TestAction());
        Menu menu = new Menu();
        menu.addTree(tree1);
        menu.addTree(tree2, tree1);
        List<Tree> expected = List.of(tree1);
        assertThat(menu.getTrees(), is(expected));
        assertThat(menu.getTrees().get(0).getBranches().get(0), is(tree2));
    }

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void whenGetAction() {
        Action action = new TestAction();
        Tree tree1 = new Tree("Tree1", action);
        Menu menu = new Menu();
        menu.addTree(tree1);
        menu.getTreeAction(tree1).makeAction();
        String expected = "Test action" + System.lineSeparator();
        assertThat(outContent.toString(), is(expected));
    }

    @Test
    public void whenGetActionByName() {
        Action action = new TestAction();
        Tree tree1 = new Tree("Tree1", action);
        Menu menu = new Menu();
        menu.addTree(tree1);
        menu.getTreeAction(tree1.getName()).makeAction();
        String expected = "Test action" + System.lineSeparator();
        assertThat(outContent.toString(), is(expected));
    }

    @Test
    public void whenPrintMenu() {
        Action action = new TestAction();
        Tree tree1 = new Tree("Tree1", action);
        Tree tree2 = new Tree("Tree1.1", action);
        Tree tree3 = new Tree("Tree2", action);
        Menu menu = new Menu();
        menu.addTree(tree1);
        menu.addTree(tree2, tree1);
        menu.addTree(tree3);
        StringBuilder expected = new StringBuilder();
        expected.append("┝").append(tree1.getName()).append(System.lineSeparator());
        expected.append("┝").append("╺╺").append(tree2.getName()).append(System.lineSeparator());
        expected.append("┝").append(tree3.getName()).append(System.lineSeparator());
        menu.show();
        assertThat(outContent.toString(), is(expected.toString()));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}
