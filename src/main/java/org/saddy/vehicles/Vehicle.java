package org.saddy.vehicles;

import org.saddy.parking.ParkingSpot;

public abstract class Vehicle {
    protected String licensePlate;
    protected VehicleSize size;

    public Vehicle(String licensePlate, VehicleSize size){
        this.licensePlate = licensePlate;
        this.size = size;
    }

    public VehicleSize getSize() {
        return size;
    }
    public String getLicensePlate() {
        return licensePlate;
    }

    public abstract boolean canFitInSpot(ParkingSpot spot);
}
