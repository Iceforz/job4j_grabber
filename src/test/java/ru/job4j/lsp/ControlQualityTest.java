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
            new GregorianCalendar(2021, Calendar.FEBRUARY, 1),
            new GregorianCalendar(2021, Calendar.FEBRUARY, 5));

    private Food garlic = new Garlic("Chinese",
            new GregorianCalendar(2021, Calendar.FEBRUARY, 5),
            new GregorianCalendar(2021, Calendar.FEBRUARY, 20));

    private Food potato = new Potato("Cherry",
            new GregorianCalendar(2021, Calendar.FEBRUARY, 1),
            new GregorianCalendar(2021, Calendar.FEBRUARY, 8));

    @Test
    public void whenLess25ToWarehouse() {
        List<Food> foods = new ArrayList<>();
        foods.add(ham);
        ControlQuality cq = new ControlQuality(List.of(warehouse, shop, trash));
        cq.setNow(new GregorianCalendar(2021, Calendar.FEBRUARY, 4).getTimeInMillis());
        cq.distribute(foods);
        List<Food> warehouseList = warehouse.clear();
        boolean warehouseRsl = warehouseList.contains(ham);
        assertThat(warehouseRsl, is(true));
    }

    @Test
    public void whenBetween25and100ToShop() {
        List<Food> foods = new ArrayList<>();
        foods.add(carrot);
        ControlQuality cq = new ControlQuality(List.of(warehouse, shop, trash));
        cq.setNow(new GregorianCalendar(2021, Calendar.FEBRUARY, 3).getTimeInMillis());
        cq.distribute(foods);
        List<Food> shopList = shop.clear();
        boolean shopRsl = shopList.contains(carrot);
        boolean carrotDiscount = carrot.isDiscount();
        assertThat(shopRsl, is(true));
        assertThat(carrotDiscount, is(false));
    }

    @Test

    public void whenMore75NoDiscount() {
        List<Food> foods = new ArrayList<>();
        foods.add(garlic);
        ControlQuality cq = new ControlQuality(List.of(warehouse, shop, trash));
        cq.setNow(new GregorianCalendar(2021, Calendar.FEBRUARY, 6).getTimeInMillis());
        cq.distribute(foods);
        List<Food> shopList = shop.clear();
        boolean shopRsl = shopList.contains(garlic);
        boolean garlicDiscount = garlic.isDiscount();
        assertThat(shopRsl, is(true));
        assertThat(garlicDiscount, is(false));
    }

    @Test
    public void whenExpiredToTrash() {
        List<Food> foods = new ArrayList<>();
        foods.add(potato);
        ControlQuality cq = new ControlQuality(List.of(warehouse, shop, trash));
        cq.setNow(new GregorianCalendar(2024, Calendar.MAY, 9).getTimeInMillis());
        cq.distribute(foods);
        List<Food> trashList = trash.clear();
        boolean trashRsl = trashList.contains(potato);
        assertThat(trashRsl, is(true));
    }

    @Test
    public void whenAddToFarmsThenResort() {
        List<Food> foods = new ArrayList<>();
        foods.add(potato);
        ControlQuality cq = new ControlQuality(List.of(warehouse, shop, trash));
        cq.setNow(new GregorianCalendar(2024, Calendar.MAY, 9).getTimeInMillis());
        cq.distribute(foods);
        List<Food> trashList = trash.clear();
        boolean trashRsl = trashList.contains(potato);
        assertThat(trashRsl, is(true));
        cq.resort();
        cq.setNow(new GregorianCalendar(2021, Calendar.FEBRUARY, 5).getTimeInMillis());
        boolean shopRsl = trashList.contains(potato);
        assertThat(shopRsl, is(true));
    }
}