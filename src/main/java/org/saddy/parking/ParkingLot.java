package org.saddy.parking;

import org.saddy.vehicles.Vehicle;
import org.saddy.vehicles.VehicleSize;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int totalSmallSpots;
    private int totalMediumSpots;
    private int totalLargeSpots;

    private int availableSmallSpots;
    private int availableMediumSpots;
    private int availableLargeSpots;

    private Map<String, Vehicle> parkedVehicles; // Track parked vehicles by license plate

    public ParkingLot(int smallSpots, int mediumSpots, int largeSpots) {
        this.totalSmallSpots = smallSpots;
        this.totalMediumSpots = mediumSpots;
        this.totalLargeSpots = largeSpots;

        this.availableSmallSpots = smallSpots;
        this.availableMediumSpots = mediumSpots;
        this.availableLargeSpots = largeSpots;

        this.parkedVehicles = new HashMap<>();
    }

    public boolean parkVehicle(Vehicle vehicle) {
        switch (vehicle.getSize()) {
            case SMALL:
                if (availableSmallSpots > 0) {
                    availableSmallSpots--;
                    parkedVehicles.put(vehicle.getLicensePlate(), vehicle);
                    System.out.println(vehicle.getLicensePlate() + " parked in a SMALL spot.");
                    return true;
                }
                break;
            case MEDIUM:
                if (availableMediumSpots > 0) {
                    availableMediumSpots--;
                    parkedVehicles.put(vehicle.getLicensePlate(), vehicle);
                    System.out.println(vehicle.getLicensePlate() + " parked in a MEDIUM spot.");
                    return true;
                }
                break;
            case LARGE:
                if (availableLargeSpots > 0) {
                    availableLargeSpots--;
                    parkedVehicles.put(vehicle.getLicensePlate(), vehicle);
                    System.out.println(vehicle.getLicensePlate() + " parked in a LARGE spot.");
                    return true;
                }
                break;
        }
        System.out.println("No available spot for " + vehicle.getLicensePlate());
        return false;
    }

    public boolean removeVehicle(Vehicle vehicle) {
        if (parkedVehicles.containsKey(vehicle.getLicensePlate())) {
            switch (vehicle.getSize()) {
                case SMALL:
                    availableSmallSpots++;
                    break;
                case MEDIUM:
                    availableMediumSpots++;
                    break;
                case LARGE:
                    availableLargeSpots++;
                    break;
            }
            parkedVehicles.remove(vehicle.getLicensePlate());
            System.out.println(vehicle.getLicensePlate() + " has been removed from the parking lot.");
            return true;
        } else {
            System.out.println("Vehicle with license plate " + vehicle.getLicensePlate() + " not found.");
            return false;
        }
    }

    public void displayAvailableSpots() {
        System.out.println("Available Spots: ");
        System.out.println("Small: " + availableSmallSpots);
        System.out.println("Medium: " + availableMediumSpots);
        System.out.println("Large: " + availableLargeSpots);
    }

    // Getter methods for testing
    public int getAvailableSmallSpots() {
        return availableSmallSpots;
    }

    public int getAvailableMediumSpots() {
        return availableMediumSpots;
    }

    public int getAvailableLargeSpots() {
        return availableLargeSpots;
    }

    public int countAvailableSpots(VehicleSize size) {
        switch (size) {
            case SMALL:
                return availableSmallSpots;
            case MEDIUM:
                return availableMediumSpots;
            case LARGE:
                return availableLargeSpots;
            default:
                throw new IllegalArgumentException("Invalid vehicle size: " + size);
        }
    }

}
