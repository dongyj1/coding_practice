package ObjectOrientedDesign.PizzaDeliver;

import java.util.List;

/**
 * Created by dyj on 7/4/18.
 */
public class Restaurant {
    private String name;
    private String location;
    private String contact;
    private int opentime;
    private int closetime;
    private Address address;
    private Menu menu;
    private List<Order> orderList;
    private int startTime;
    private int endTime;
    private int maxNumOfOrders;

    public boolean isOpen(int currentTime) {
        return currentTime > startTime && currentTime < endTime;
    }

    public void updateMenu(Menu menu) {
        this.menu = menu;
    }

    public boolean addNewPizzaToMenu(Pizza p) {
        boolean res = menu.addItem(p);
        if (res) {
            displayMenu();
        }
        return res;
    }

    public boolean deletePizzaFromMenu(Pizza p) {
        boolean res = menu.removeItem(p);
        if (res) {
            displayMenu();
        }
        return res;
    }

    public boolean placeOrder(Order o) {
        if (orderList.size() >= maxNumOfOrders) {
            return false;
        }
        return true;
    }

    public boolean cancalOrder(Order o) {
        orderList.remove(o);
        return true;
    }

    private Menu displayMenu() {
        return menu;
    }
}
