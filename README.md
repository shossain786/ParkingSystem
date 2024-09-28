# Parking System

### About the Project

This Project is to build a Parking Lot System, with features like add cars, remove cars and checking for available spots. 
The Parking lot should support different sizes, such as small, medium and large and accomodate different types of vehicles
based on their sizes.


### Problem Description :-
<hr>

1) The Parking lot has a fixed number of spots for small, medium and large vehicles.
2) Vehicles are of three types:
        a. Small Vehicle
        b. Medium Vehicle
        c. Large Vehicle
Program should handle adding vehicles to the parking lot, removing them and checking available spots for each vehicle size.

### Test To Check The System:-
<hr>

```java
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
```
### Output Of The Test Program
<hr>
```text
MH12S2012 parked in a SMALL spot.
MH12S3412 parked in a MEDIUM spot.
MH12S1002 parked in a LARGE spot.
MH14V0982 parked in a SMALL spot.
Available Spots: 
Small: 0
Medium: 1
Large: 1
MH12S2012 has left the parking.
MH14V0982 has left the parking.
MH12S3412 has left the parking.
MH12S1002 has left the parking.
Available Spots: 
Small: 2
Medium: 2
Large: 2
```