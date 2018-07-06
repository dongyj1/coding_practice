package ObjectOrientedDesign.ParkingLot;

/**
 * Created by dyj on 7/4/18.
 */
public class Car extends Vehicle {

    public Car() {
        spotsNeeded = 1;
        type = Type.CAR;
    }

    @Override
    public boolean canFit(ParkingSpot ps) {
        return false;
    }
}
