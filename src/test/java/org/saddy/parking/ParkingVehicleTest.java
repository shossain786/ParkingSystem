package org.saddy.parking;

import org.saddy.vehicles.LargeCar;
import org.saddy.vehicles.MediumCar;
import org.saddy.vehicles.SmallCar;
import org.saddy.vehicles.Vehicle;
import org.testng.annotations.Test;

public class ParkingVehicleTest {
    @Test
    public void parkingVehicleTest() {
        ParkingLot parkingLot = new ParkingLot(2,2,2);

        Vehicle smallCar = new SmallCar("MH12S2012");
        Vehicle mediumCar = new MediumCar("MH12S3412");
        Vehicle largeCar = new LargeCar("MH12S1002");
        Vehicle smallCar1 = new SmallCar("MH14V0982");

        parkingLot.parkVehicle(smallCar);
        parkingLot.parkVehicle(mediumCar);
        parkingLot.parkVehicle(largeCar);
        parkingLot.parkVehicle(smallCar1);

        parkingLot.displayAvailableSpots();

        parkingLot.removeVehicle(smallCar);
        parkingLot.removeVehicle(smallCar1);
        parkingLot.removeVehicle(mediumCar);
        parkingLot.removeVehicle(largeCar);

        parkingLot.displayAvailableSpots();
    }
}
