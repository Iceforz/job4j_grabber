package ru.job4j.lsp;

import java.util.stream.IntStream;

public class ParkTransport implements Parking {
    private final Transport[] passengerCarPlaces;
    private final Transport[] truckPlaces;

    public ParkTransport(int passengerCarPlaces, int truckPlaces) {
        this.passengerCarPlaces = new Transport[passengerCarPlaces];
        this.truckPlaces = new Transport[truckPlaces];
}

    @Override
    public Transport[] getPassengerCarPlaces() {
        return passengerCarPlaces;
    }

    @Override
    public Transport[] getTruckPlaces() {
        return truckPlaces;
    }

    @Override
    public boolean getPlace(Transport transport) {
        boolean rsl = false;
        if (transport.getSize() == 1) {
            rsl = park(transport, passengerCarPlaces);
        }
        if (transport.getSize() > 1) {
            if (park(transport, truckPlaces)) {
                rsl = true;
            } else {
                int i = findFreePlaceBySize(transport.getSize());
                if (i != -1) {
                    for (int j = 0; j < transport.getSize(); j++) {
                        passengerCarPlaces[i + j] = transport;
                    }
                    rsl = true;
                }
            }
        }
        return rsl;
    }

    @Override
    public void leftPlace(Transport transport) {
        if (transport.getSize() == 1 && !left(transport, passengerCarPlaces)) {
            System.out.println("Place is free");
        }
        if (transport.getSize() > 1 && !left(transport, truckPlaces) && !left(
                transport, passengerCarPlaces)) {
            System.out.println("Place is free");
        }
    }

    private boolean left(Transport transport, Transport[] transports) {
        boolean rsl = false;
        for (int i = 0; i < transports.length; i++) {
            if (transport.equals(transports[i])) {
                transports[i] = null;
                rsl = true;
            }
        }
        return rsl;
    }

    private boolean park(Transport transport, Transport[] places) {
        boolean rsl = false;
        int i = findFreePlace(places);
        if (i != -1) {
            places[i] = transport;
            rsl = true;
        }
        return rsl;
    }

    private int findFreePlace(Transport[] places) {
        return IntStream.range(0, places.length).filter(i -> places[i] == null).
                findFirst().orElse(-1);
    }

    private int findFreePlaceBySize(int size) {
        int rsl = -1, counter = 0, foundPlaces = 0;
        for (int i = 0; i <= passengerCarPlaces.length - size; i++) {
            while (counter < size) {
                if (passengerCarPlaces[i + counter] == null) {
                    foundPlaces++;
                }
                counter++;
            }
            if (counter == foundPlaces) {
                rsl = i;
                break;
            } else {
                counter = 0;
                foundPlaces = 0;
            }
        }
        return rsl;
    }
}
