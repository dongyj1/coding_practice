package ObjectOrientedDesign.PizzaDeliver;

import java.util.Map;

/**
 * Created by dyj on 7/4/18.
 */
public class Order {
    Status status = Status.Unpaid;
    private int time;
    Map<Pizza, Integer> pizzaIntegerMap;
    Address address;
    Customer owner;

    public void setTime(int time) {
        this.time = time;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public int getTime() {
        return time;
    }

    public void addPizza(Pizza p, int count) {
        pizzaIntegerMap.put(p, count);
    }

    public float getSum() {
        float sum = 0f;
        for (Map.Entry entry : pizzaIntegerMap.entrySet()) {
            sum += ((Pizza)entry.getKey()).getPrice() * (int)entry.getValue();
        }
        return sum;
    }

    public float checkout() {
        status = Status.Placed;
        return getSum();
    }

    public void shipped() {
        status = Status.Shipped;
    }

    public void delivered() {
        status = Status.Delivered;
    }

    enum Status {
        Unpaid,
        Placed,
        Shipped,
        Delivered;
    }
}