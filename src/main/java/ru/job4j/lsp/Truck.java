package ru.job4j.lsp;

public class Truck implements Transport {
    private int size;
    private final String model;
    private int id;

    public Truck(int size, String model, int id) {
        this.size = size;
        this.model = model;
        this.id = id;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Truck{" + "id='" + id + '\'' + '}';
    }
}
