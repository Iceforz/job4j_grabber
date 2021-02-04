package ru.job4j.lsp;
import java.util.List;

public class ControlQuality {
    private final List<Farm> farms;
    private Long now;

    public ControlQuality(List<Farm> farms) {
        this.farms = farms;
    }

    public void distribute(Food food) {
        for (Farm farm : farms) {
            if (farm.accept(food)) {
                farm.addFood(food);
            }
            }
        }

    public void setNow(Long currentTimeForTest) {
        this.now = currentTimeForTest;
    }
}