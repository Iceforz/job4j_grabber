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
        this.list.add(food);
    }

    @Override
    public boolean accept(Food food) {
        double rsl = percentageSpent(food);
        if (rsl > 0.25 || rsl < 1) {
              return true;
          } else if (rsl >= 0.75 && rsl < 1) {
               food.setDiscount(food.isDiscount());
              return true;
          } else {
              return false;
         }
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