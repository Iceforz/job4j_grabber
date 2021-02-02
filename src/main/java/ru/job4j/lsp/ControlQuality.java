package ru.job4j.lsp;
import java.util.List;

public class ControlQuality {
    private Farm warehouse;
    private Farm shop;
    private Farm trash;
    private Long now;

    public ControlQuality(Farm warehouse, Farm shop, Farm trash) {
        this.warehouse = warehouse;
        this.shop = shop;
        this.trash = trash;
    }

    public void setNow(Long currentTimeForTest) {
        this.now = currentTimeForTest;
    }

    public void toFarm(List<Food> foods) {
        if (now == 0) {
            now = System.currentTimeMillis();
        }
        for (Food food:foods) {
            long createDate = food.getCreateDate().getTimeInMillis();
            long expiredDate = food.getExpiryDate().getTimeInMillis();
            double lifeTime = (double) (expiredDate - createDate);
            double timePassed = (double) (now - createDate);
            if (lifeTime > timePassed) {
                double expRatio = timePassed / lifeTime;
                if (expRatio < 0.25) {
                    warehouse.addFood(food);
                } else if (expRatio >= 0.25 && expRatio < 0.75) {
                    shop.addFood(food);
                } else if (expRatio >= 0.75) {
                    food.setDiscount(true);
                    shop.addFood(food);
                }
            } else {
                trash.addFood(food);
            }
        }
    }
}
