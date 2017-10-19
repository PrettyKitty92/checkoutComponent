package patrycja.szelc.basket.component.model;

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

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "special_price")
    private SpecialPrice specialPrice;

    public Item() {
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
        return specialPrice;
    }
}
