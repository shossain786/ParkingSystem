package org.saddy.vehicles;

public class SmallCar extends Vehicle{
    public SmallCar(String licensePlate) {
        super(licensePlate, VehicleSize.SMALL);
    }

    @Override
    public boolean canFitInSpot(ParkingSpot spot) {
        return spot.getSize() == VehicleSize.SMALL ||
                spot.getSize() == VehicleSize.MEDIUM ||
                spot.getSize() == VehicleSize.LARGE;
    }
}
