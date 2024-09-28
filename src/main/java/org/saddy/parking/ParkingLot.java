package org.saddy.parking;

import org.saddy.vehicles.ParkingSpot;
import org.saddy.vehicles.Vehicle;
import org.saddy.vehicles.VehicleSize;

//Class to Manage Spots
public class ParkingLot {
    private ParkingSpot[] smallSpots;
    private ParkingSpot[] mediumSpot;
    private ParkingSpot[] largeSpot;

    public ParkingLot(int smallCount, int mediumCount, int largeCount) {
        smallSpots = new ParkingSpot[smallCount];
        mediumSpot = new ParkingSpot[mediumCount];
        largeSpot = new ParkingSpot[largeCount];

        for (int i = 0; i < smallCount; i++) {
            smallSpots[i] = new ParkingSpot(VehicleSize.SMALL);
        }
        for (int i = 0; i< mediumCount; i++)
            mediumSpot[i] = new ParkingSpot(VehicleSize.MEDIUM);

        for (int i = 0; i< largeCount; i++)
            largeSpot[i] = new ParkingSpot(VehicleSize.LARGE);
    }

    public boolean parkVehicle(Vehicle vehicle){
        if (vehicle.getSize() == VehicleSize.SMALL)
            return parkInAnySpot(vehicle, smallSpots, mediumSpot, largeSpot);
        else if (vehicle.getSize() == VehicleSize.MEDIUM) {
            return parkInAnySpot(vehicle, mediumSpot, largeSpot);
        } else {
            return parkInAnySpot(vehicle, largeSpot);
        }
    }

    private boolean parkInAnySpot(Vehicle vehicle, ParkingSpot[]... spotLevel) {
        for (ParkingSpot[] spots : spotLevel) {
            for (ParkingSpot spot : spots) {
                if (spot.park(vehicle)) {
                    System.out.println(vehicle.getLicensePlate() + " parked in a " + spot.getSize() + " spot.");
                    return true;
                }
            }
        }
        System.out.println("Sorry! No available spot for vehicle: " + vehicle.getLicensePlate());
        return false;
    }

    public void removeVehicle(Vehicle vehicle) {
        if (vehicle.getSize() == VehicleSize.SMALL) {
            removeFromSpot(vehicle, smallSpots, mediumSpot, largeSpot);
        } else if (vehicle.getSize() == VehicleSize.MEDIUM) {
            removeFromSpot(vehicle, mediumSpot, largeSpot);
        } else {
            removeFromSpot(vehicle, largeSpot);
        }
    }

    private void removeFromSpot(Vehicle vehicle, ParkingSpot[]... spotLevels){
        for (ParkingSpot[] spots: spotLevels) {
            for (ParkingSpot spot: spots) {
                if (!spot.isAvailable() && spot.getCurrentVehicle() != null &&
                spot.getCurrentVehicle().getLicensePlate().equals(vehicle.getLicensePlate())) {
                    spot.leave();
                    System.out.println(vehicle.getLicensePlate() + " has left the parking.");
                    return;
                }
            }
        }
        System.out.println("Vehicle " + vehicle.getLicensePlate() + " not found in any spot.");
    }

    public void displayAvailableSpots() {
        System.out.println("Available Spots: ");
        System.out.println("Small: " + countAvailableSpot(smallSpots));
        System.out.println("Medium: " + countAvailableSpot(mediumSpot));
        System.out.println("Large: " + countAvailableSpot(largeSpot));
    }
    private int countAvailableSpot(ParkingSpot[] spots) {
        int count = 0;
        for (ParkingSpot spot: spots) {
            if (spot.isAvailable()) {
                count ++;
            }
        }
        return count;
    }
}
