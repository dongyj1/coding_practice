package ObjectOrientedDesign.ParkingLot;

/**
 * Created by dyj on 7/4/18.
 */
public class ParkingSpot {

    private Vehicle vehicle;
    private Type type;
    private int row;
    private int spotNumber;
    private Level level;

    public ParkingSpot(Level lvl, int r, int n, Type t){
        this.level = lvl;
        this.row = r;
        this.spotNumber = n;
        this.type = t;
    }

    public boolean isAvailable() {
        return vehicle == null;
    }

    // check if the parking spot can fit
    public boolean canFit(Vehicle vehicle) {
        return vehicle.canFit(this);
    }

    public boolean park(Vehicle vehicle) {
        return true;
    }

    // remove vehicle from spot and notify level thatn a new spot is available
    public void remove(Vehicle v) {

    }

    public int getRow() {
        return row;
    }

    public int getSpotNumber() {
        return spotNumber;
    }
}
