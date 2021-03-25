package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Farm {
    private List<Food> list = new ArrayList<>();
    private String country;

    public Trash(String country) {
        this.country = country;
    }

    @Override
    public void addFood(Food food) {
        this.list.add(food);
    }

    @Override
    public boolean accept(Food food) {
        return percentageSpent(food) >= 1;
    }

    @Override
    public List<Food> clear() {
        List<Food> listReturn = new ArrayList<>(list);
        list.clear();
        return listReturn;
    }

    @Override
    public List<Food> get() {
        return list;
    }
}

