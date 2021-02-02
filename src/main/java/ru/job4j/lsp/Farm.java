package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;

public class Farm {
    private List<Food> farm = new ArrayList<>();

    public void addFood(Food food) {
        farm.add(food);
    }

    public List<Food> showFood() {
        return farm;
    }
}


