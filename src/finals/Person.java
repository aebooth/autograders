package finals;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 6/14/17.
 */
public class Person {
    public final double MAX_WEIGHT;
    public final List<Food> foodItems;

    public Person(double maxWeight){
        MAX_WEIGHT = maxWeight;
        this.foodItems = new ArrayList<>();
    }
}
