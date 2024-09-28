package org.saddy.vehicles;

public class LargeCar extends Vehicle{
    public LargeCar(String licencePlate) {
        super(licencePlate, VehicleSize.LARGE);
    }
    @Override
    public boolean canFitInSpot(ParkingSpot spot) {
        return spot.getSize() == VehicleSize.LARGE;
    }
}
