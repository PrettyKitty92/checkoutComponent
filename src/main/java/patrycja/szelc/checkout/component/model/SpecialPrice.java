package patrycja.szelc.checkout.component.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class SpecialPrice implements Serializable {


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
