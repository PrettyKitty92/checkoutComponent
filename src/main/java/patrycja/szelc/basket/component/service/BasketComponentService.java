package patrycja.szelc.basket.component.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BasketComponentService implements BasketComponentInterface {

    private PriceCalculator priceCalculator;
    private Map<String, ItemCounter> items = new HashMap<String, ItemCounter>();
    private int totalPrice = 0;

    @Autowired
    public BasketComponentService(PriceCalculator priceCalculator) {
        this.priceCalculator = priceCalculator;
    }

    @Override
    public void addItem(String name) {

        items.putIfAbsent(name, new ItemCounter());
        items.get(name).increase();

        totalPrice = priceCalculator.calculateTotalPrice(items);

    }

    @Override
    public void deleteItem(String name) {

        items.get(name).decrease();

        totalPrice = priceCalculator.calculateTotalPrice(items);

    }

    @Override
    public void clear() {
        items.clear();
        totalPrice = 0;
    }

    @Override
    public int checkoutTotalPrice() {
        return totalPrice;
    }
}
