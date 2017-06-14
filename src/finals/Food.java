package finals;

/**
 * Created by Alex on 6/14/17.
 */
public class Food {
    public final double price;
    public final double weight;
    public final String name;

    public Food(String name, double price, double weight){
        this.price = price;
        this.name = name;
        this.weight = weight;
    }
}
