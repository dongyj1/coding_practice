package ObjectOrientedDesign.PizzaDeliver;

import java.util.List;

/**
 * Created by dyj on 7/4/18.
 */
public class Menu {
    List<Pizza> pizzaList;
    public boolean addItem(Pizza p) {
        pizzaList.add(p);
        return true;
    }

    public boolean removeItem(Pizza p) {
        pizzaList.remove(p);
        return true;
    }
}
