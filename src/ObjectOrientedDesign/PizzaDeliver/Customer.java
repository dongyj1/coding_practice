package ObjectOrientedDesign.PizzaDeliver;

import java.util.List;

/**
 * Created by dyj on 7/4/18.
 */
public class Customer {
    private String name;
    private String contact;
    private Address address;
    List<Order> orders;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    // find a nearby restaurant by some condition
    public Restaurant findRestaurant() {
        return null;
    }

    public void placeOrder(Order order) {
        orders.add(order);
    }


}
