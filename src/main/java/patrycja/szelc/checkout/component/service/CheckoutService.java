package patrycja.szelc.checkout.component.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CheckoutService implements CheckoutComponentInterface {

    private PriceCalculator priceCalculator;
    private Map<String, Counter> items = new HashMap<String, Counter>();
    private int totalPrice = 0;

    @Autowired
    public CheckoutService(PriceCalculator priceCalculator) {
        this.priceCalculator = priceCalculator;
    }

    @Override
    public void addItem(String name) {

        items.putIfAbsent(name, new Counter());
        items.get(name).increase();

        totalPrice = priceCalculator.calculateTotalPrice(items);

    }

    @Override
    public void deleteItem(String name) {

        items.get(name).decrease();

        totalPrice = priceCalculator.calculateTotalPrice(items);

    }

    @Override
    public void deleteAll() {
        items.clear();
        totalPrice = 0;
    }

    @Override
    public int checkoutTotalPrice() {
        return totalPrice;
    }
}
