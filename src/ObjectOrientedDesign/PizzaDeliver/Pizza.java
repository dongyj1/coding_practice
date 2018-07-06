package ObjectOrientedDesign.PizzaDeliver;

/**
 * Created by dyj on 7/4/18.
 */
public class Pizza {
    Type type;
    double price;
    int size;
    String description;
    Restaurant restaurant;

    public void setType(Type type) {
        this.type = type;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Type getType() {

        return type;
    }

    public double getPrice() {
        return price;
    }

    public int getSize() {
        return size;
    }

    public String getDescription() {
        return description;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
}
enum Type {
    Italian("Italian"),
    California("Californian"),
    Greek("Greek");

    private String name;
    Type(String n) {
        name = n;
    }
}
