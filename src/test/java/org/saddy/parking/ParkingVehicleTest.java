package org.saddy.parking;

import org.saddy.vehicles.LargeCar;
import org.saddy.vehicles.MediumCar;
import org.saddy.vehicles.SmallCar;
import org.saddy.vehicles.Vehicle;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ParkingVehicleTest {

    private ParkingLot parkingLot;

    @BeforeMethod
    public void setup() {
        // Initialize the parking lot with 2 spots each for small, medium, and large cars
        parkingLot = new ParkingLot(2, 2, 2);
    }

    @Test
    public void testParkingAndRemovingVehicles() {
        Vehicle smallCar = new SmallCar("MH12S2012");
        Vehicle mediumCar = new MediumCar("MH12S3412");
        Vehicle largeCar = new LargeCar("MH12S1002");
        Vehicle smallCar1 = new SmallCar("MH14V0982");

        // Test parking the vehicles
        Assert.assertTrue(parkingLot.parkVehicle(smallCar), "Failed to park Small Car 1");
        Assert.assertTrue(parkingLot.parkVehicle(mediumCar), "Failed to park Medium Car");
        Assert.assertTrue(parkingLot.parkVehicle(largeCar), "Failed to park Large Car");
        Assert.assertTrue(parkingLot.parkVehicle(smallCar1), "Failed to park Small Car 2");

        // Verify available spots after parking
        Assert.assertEquals(parkingLot.getAvailableSmallSpots(), 0, "Incorrect available small spots after parking.");
        Assert.assertEquals(parkingLot.getAvailableMediumSpots(), 1, "Incorrect available medium spots after parking.");
        Assert.assertEquals(parkingLot.getAvailableLargeSpots(), 1, "Incorrect available large spots after parking.");

        parkingLot.displayAvailableSpots();

        // Test removing the vehicles
        Assert.assertTrue(parkingLot.removeVehicle(smallCar), "Failed to remove Small Car 1");
        Assert.assertTrue(parkingLot.removeVehicle(smallCar1), "Failed to remove Small Car 2");
        Assert.assertTrue(parkingLot.removeVehicle(mediumCar), "Failed to remove Medium Car");
        Assert.assertTrue(parkingLot.removeVehicle(largeCar), "Failed to remove Large Car");

        // Verify available spots after removing
        Assert.assertEquals(parkingLot.getAvailableSmallSpots(), 2, "Incorrect available small spots after removal.");
        Assert.assertEquals(parkingLot.getAvailableMediumSpots(), 2, "Incorrect available medium spots after removal.");
        Assert.assertEquals(parkingLot.getAvailableLargeSpots(), 2, "Incorrect available large spots after removal.");

        parkingLot.displayAvailableSpots();
    }

    @Test
    public void testParkingWhenFull() {
        Vehicle smallCar = new SmallCar("MH12S2012");
        Vehicle smallCar1 = new SmallCar("MH14V0982");
        Vehicle smallCar2 = new SmallCar("MH15A1234");  // This car should fail to park

        Assert.assertTrue(parkingLot.parkVehicle(smallCar), "Failed to park Small Car 1");
        Assert.assertTrue(parkingLot.parkVehicle(smallCar1), "Failed to park Small Car 2");

        // Since the small car spots are full, parking another small car should fail
        Assert.assertFalse(parkingLot.parkVehicle(smallCar2), "Unexpected success in parking when no spots are available.");

        parkingLot.displayAvailableSpots();
    }
}
