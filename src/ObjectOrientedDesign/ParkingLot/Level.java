package ObjectOrientedDesign.ParkingLot;

/**
 * Created by dyj on 7/4/18.
 */
public class Level {
    private int floor;
    private ParkingSpot[] spots;
    private int availableSpots = 0; // freespot;
    private static final int SPOTS_PER_ROW = 10;

    public Level(int f, int numSpots) {
        floor = f;
    }
}
