package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Farm {
    private List<Food> list = new ArrayList<>();
    private String country;

    public Warehouse(String country) {
        this.country = country;
    }

    @Override
    public void addFood(Food food) {
        list.add(food);
    }

    @Override
    public boolean accept(Food food) {
        return percentageSpent(food) < 0.25;
    }

    @Override
    public List<Food> clear() {
        List<Food> listReturn = list;
        list.clear();
        return listReturn;
    }
}
