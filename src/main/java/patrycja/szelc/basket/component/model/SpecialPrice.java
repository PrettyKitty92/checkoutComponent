package patrycja.szelc.basket.component.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class SpecialPrice implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private int quantity;
    private int value;

    public SpecialPrice() {
    }

    public SpecialPrice(int quantity, int value) {
        this.quantity = quantity;
        this.value = value;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getValue() {
        return value;
    }
}
