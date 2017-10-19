package patrycja.szelc.checkout.component.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "item")
public class Item implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(name = "special_price", nullable = true)
    @Embedded
    @Basic(optional = true)
    private SpecialPrice specialPrice;

    protected Item() {
    }


    public Item(String name, int price, SpecialPrice specialPrice) {
        this.name = name;
        this.price = price;
        this.specialPrice = specialPrice;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public SpecialPrice getSpecialPrice() {
        return specialPrice.getQuantity() != 0 ? specialPrice : null;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", specialPrice=" + specialPrice +
                '}';
    }
}
