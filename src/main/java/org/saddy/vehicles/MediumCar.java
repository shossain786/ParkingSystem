package org.saddy.vehicles;

import org.saddy.parking.ParkingSpot;

public class MediumCar extends  Vehicle{
    public MediumCar(String licensePlate){
        super(licensePlate, VehicleSize.MEDIUM);
    }
    @Override
    public boolean canFitInSpot(ParkingSpot spot) {
        return spot.getSize() == VehicleSize.MEDIUM ||
                spot.getSize() == VehicleSize.LARGE;
    }
}
