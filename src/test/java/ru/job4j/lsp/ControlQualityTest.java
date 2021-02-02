package ru.job4j.lsp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ControlQualityTest {
    private Farm warehouse = new Warehouse("USA");
    private Farm shop = new Shop("Canada");
    private Farm trash = new Trash("GB");

    private Food ham = new Ham("Italian",
            new GregorianCalendar(2021, Calendar.FEBRUARY, 4),
            new GregorianCalendar(2022, Calendar.FEBRUARY, 4));

    private Food carrot = new Carrot("Baby carrot",
            new GregorianCalendar(2021, Calendar.FEBRUARY, 2),
            new GregorianCalendar(2021, Calendar.FEBRUARY, 6));

    private Food garlic = new Garlic("Chinese",
            new GregorianCalendar(2021, Calendar.FEBRUARY, 23),
            new GregorianCalendar(2021, Calendar.FEBRUARY, 28));

    private Food potato = new Potato("Cherry",
            new GregorianCalendar(2021, Calendar.FEBRUARY, 1),
            new GregorianCalendar(2021, Calendar.FEBRUARY, 8));

    @Test
    public void whenLess25ToWarehouse() {
        List<Food> foods = new ArrayList<>();
        foods.add(ham);
        ControlQuality cq = new ControlQuality(warehouse, shop, trash);
        cq.setNow(new GregorianCalendar(2021, Calendar.FEBRUARY, 15).getTimeInMillis());
        cq.toFarm(foods);
        List<Food> warehouseList = warehouse.showFood();
        boolean warehouseRsl = warehouseList.contains(ham);
        assertThat(warehouseRsl, is(true));
    }

    @Test
    public void whenBetween25and75ToShop() {
        List<Food> foods = new ArrayList<>();
        foods.add(carrot);
        ControlQuality cq = new ControlQuality(warehouse, shop, trash);
        cq.setNow(new GregorianCalendar(2021, Calendar.FEBRUARY, 4).getTimeInMillis());
        cq.toFarm(foods);
        List<Food> shopList = shop.showFood();
        boolean shopRsl = shopList.contains(carrot);
        boolean carrotDiscount = carrot.isDiscount();
        assertThat(shopRsl, is(true));
        assertThat(carrotDiscount, is(false));
    }

    @Test
    public void whenMore75SetDiscount() {
        List<Food> foods = new ArrayList<>();
        foods.add(garlic);
        ControlQuality cq = new ControlQuality(warehouse, shop, trash);
        cq.setNow(new GregorianCalendar(2021, Calendar.FEBRUARY, 27).getTimeInMillis());
        cq.toFarm(foods);
        List<Food> shopList = shop.showFood();
        boolean shopRsl = shopList.contains(garlic);
        boolean garlicDiscount = garlic.isDiscount();
        assertThat(shopRsl, is(true));
        assertThat(garlicDiscount, is(true));
    }

    @Test
    public void whenExpiredToTrash() {
        List<Food> foods = new ArrayList<>();
        foods.add(potato);
        ControlQuality cq = new ControlQuality(warehouse, shop, trash);
        cq.setNow(new GregorianCalendar(2022, Calendar.FEBRUARY, 9).getTimeInMillis());
        cq.toFarm(foods);
        List<Food> trashList = trash.showFood();
        boolean trashRsl = trashList.contains(potato);
        assertThat(trashRsl, is(true));
    }
}