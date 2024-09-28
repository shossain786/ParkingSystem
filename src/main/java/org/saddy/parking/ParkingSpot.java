package org.saddy.parking;

import org.saddy.vehicles.Vehicle;
import org.saddy.vehicles.VehicleSize;

public class ParkingSpot {
    private VehicleSize size;
    private Vehicle currentVehicle;

    public ParkingSpot(VehicleSize size){
        this.size = size;
        this.currentVehicle = null;
    }

    public VehicleSize getSize() {
        return size;
    }

    public boolean isAvailable(){
        return currentVehicle == null;
    }

    public boolean park(Vehicle vehicle) {
        if (isAvailable() && vehicle.canFitInSpot(this)) {
            this.currentVehicle = vehicle;
            return true;
        }
        return false;
    }

    public void leave(){
        this.currentVehicle = null;
    }

    public Vehicle getCurrentVehicle(){
        return this.currentVehicle;
    }
}
