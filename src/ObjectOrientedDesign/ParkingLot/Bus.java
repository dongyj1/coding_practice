package ObjectOrientedDesign.ParkingLot;

/**
 * Created by dyj on 7/4/18.
 */
public class Bus extends Vehicle {

    public Bus() {
        spotsNeeded = 2;
        type = Type.BUS;
    }

    @Override
    public boolean canFit(ParkingSpot ps) {
        return false;
    }
}
