package ru.job4j.lsp;

public interface Parking {

    Transport[] getPassengerCarPlaces();
//паркова легковой

    Transport[] getTruckPlaces();
//парковка грузовой

    boolean getPlace(Transport transport);
//занял место

    void leftPlace(Transport transport);
//покинул место
}

