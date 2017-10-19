package patrycja.szelc.basket.component.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import patrycja.szelc.basket.component.model.Item;
import patrycja.szelc.basket.component.model.SpecialPrice;
import patrycja.szelc.basket.component.repository.ItemRepository;

import java.util.Map;

@Component
public class PriceCalculator {

    private ItemRepository itemRepository;

    @Autowired
    public PriceCalculator(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    int calculateTotalPrice(Map<String, ItemCounter> items) {
        int result = 0;

        for (Map.Entry<String, ItemCounter> entry : items.entrySet()) {
            Item item = itemRepository.findByName(entry.getKey());
            if (item.getSpecialPrice() != null) {
                SpecialPrice specialPrice = item.getSpecialPrice();
                if (entry.getValue().getCount() >= specialPrice.getQuantity()) {
                    result += (entry.getValue().getCount() / specialPrice.getQuantity()) * specialPrice.getValue();
                    result += (entry.getValue().getCount() % specialPrice.getQuantity()) * item.getPrice();
                } else {
                    result += entry.getValue().getCount() * item.getPrice();
                }
            } else {
                result += entry.getValue().getCount() * item.getPrice();
            }
        }
        return result;


    }
}
