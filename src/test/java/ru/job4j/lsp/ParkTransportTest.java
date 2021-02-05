package ru.job4j.lsp;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkTransportTest {

    @Test
    public void whenParkOnFreeSpot() {
        Parking parking = new ParkTransport(1, 1);
        Transport passengerCar = new PassengerCar(1, "car1", 1);
        Transport truck = new Truck(2, "truck1", 2);
        assertTrue(parking.getPlace(passengerCar));
        assertTrue(parking.getPlace(truck));
    }

    @Test
    public void whenLeftSpot() {
        Parking parking = new ParkTransport(1, 1);
        Transport passengerCar = new PassengerCar(1, "car1", 1);
        Transport truck = new Truck(2, "truck1", 2);
        parking.getPlace(passengerCar);
        parking.getPlace(truck);
        parking.leftPlace(passengerCar);
        parking.leftPlace(truck);
        assertNull(parking.getPassengerCarPlaces()[0]);
        assertNull(parking.getTruckPlaces()[0]);
    }

    @Test
    public void whenTruckGetsOnPassengerCarPlace() {
        Parking parking = new ParkTransport(2, 0);
        Transport truck = new Truck(2, "truck1", 2);
        parking.getPlace(truck);
        assertEquals(parking.getPassengerCarPlaces()[0], truck);
        assertEquals(parking.getPassengerCarPlaces()[1], truck);
    }
}