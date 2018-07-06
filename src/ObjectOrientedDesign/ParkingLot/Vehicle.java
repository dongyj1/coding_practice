package ObjectOrientedDesign.ParkingLot;

import java.util.List;

/**
 * Created by dyj on 7/4/18.
 */
public abstract class Vehicle {
    protected List<ParkingSpot> parkingSpots;
    protected String licence;
    protected int spotsNeeded;
    protected Type type;

    public int getSpotsNeeded() {
        return spotsNeeded;
    }

    // park a vehicle in this spot
    public void parkInSpot(ParkingSpot ps) {

    }

    public void clearSpot() {

    }

    public abstract boolean canFit(ParkingSpot ps);
}
