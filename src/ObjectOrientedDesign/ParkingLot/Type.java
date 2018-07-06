package ObjectOrientedDesign.ParkingLot;

/**
 * Created by dyj on 7/4/18.
 */
public enum Type {
    MOTORCYCLE("Motorcycle"),
    CAR("Car"),
    BUS("Bus");

        private final String name = "";
//
    private Type(String name) {

    }

    public String toString(){
        return this.name;
    }

    public static Type random() {
        int i = (int) (Math.random() * Type.values().length);
        return Type.values()[i];
    }
}
