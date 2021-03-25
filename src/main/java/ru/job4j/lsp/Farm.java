package ru.job4j.lsp;

import java.util.Calendar;
import java.util.List;

public interface Farm {
     void addFood(Food food);

    boolean accept(Food food);

    List<Food> clear();

    List<Food> get();

    default double percentageSpent(Food food) {
        long now = Calendar.getInstance().getTimeInMillis();
        long expired = food.getExpiryDate().getTimeInMillis();
        long created = food.getCreateDate().getTimeInMillis();
        return (double) (now - created) / (expired - created);

    }
}





