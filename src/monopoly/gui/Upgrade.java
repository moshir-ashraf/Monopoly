
package monopoly.gui;
import java.io.Serializable;
public class Upgrade implements Serializable {
    int cost;
    int rent_addition;
    Upgrade(String cost, String rent_addition){
        this.cost = Integer.parseInt(cost);
        this.rent_addition = Integer.parseInt(rent_addition);
    }
}
