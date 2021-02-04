package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Farm {
    private List<Food> list = new ArrayList<>();
    private String country;

    public Shop(String country) {
        this.country = country;
    }

    @Override
    public void addFood(Food food) {
        list.add(food);
    }

    @Override
    public boolean accept(Food food) {
        double check = percentageSpent(food);
        if (check >= 0.25 && check < 1) {
            food.setDiscount(food.isDiscount());
        }
        return check > 0.25 && check < 1;
    }

    @Override
    public List<Food> clear() {
        List<Food> listReturn = list;
        list.clear();
        return listReturn;
    }
}