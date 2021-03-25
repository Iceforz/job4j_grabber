package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private final List<Farm> farms;
    private Long now;

    public ControlQuality(List<Farm> farms) {
        this.farms = farms;
    }

    public void distribute(List<Food> foods) {
        for (Food food : foods) {
            for (Farm farm : farms) {
                if (farm.accept(food)) {
                    farm.addFood(food);
                }
            }
        }
    }

    public void setNow(Long currentTimeForTest) {
        this.now = currentTimeForTest;
    }

    public void resort() {
        List<Food> listReturn = new ArrayList<>();
        for (Farm farm : farms) {
            listReturn.addAll(farm.get());
            farm.clear();
        }
        this.distribute(listReturn);
    }
}
