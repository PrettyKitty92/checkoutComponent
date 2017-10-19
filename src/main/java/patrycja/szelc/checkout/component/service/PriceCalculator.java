package patrycja.szelc.checkout.component.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import patrycja.szelc.checkout.component.model.Item;
import patrycja.szelc.checkout.component.model.SpecialPrice;
import patrycja.szelc.checkout.component.repository.ItemRepository;

import java.util.Map;

@Component
public class PriceCalculator {

    private ItemRepository itemRepository;

    @Autowired
    public PriceCalculator(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    int calculateTotalPrice(Map<String, Counter> items) {
        int result = 0;

        for (Map.Entry<String, Counter> entry : items.entrySet()) {
            Item item = itemRepository.findByName(entry.getKey());
            if (item.getSpecialPrice() != null) {
                SpecialPrice specialPrice = item.getSpecialPrice();
                if (entry.getValue().getCount() > specialPrice.getQuantity()) {
                    result += (entry.getValue().getCount() / specialPrice.getQuantity()) * specialPrice.getValue();
                    result += (entry.getValue().getCount() % specialPrice.getQuantity()) * item.getPrice();
                }
            }
        }
        return result;

        /* items.forEach((name, counter) -> {
            Item item = itemRepository.findByName(name);
            if(item.getSpecialPrice()!=null){
                SpecialPrice specialPrice = item.getSpecialPrice();
                if(counter.getQuantity()>specialPrice.getQuantity()){
                    result += (counter.getQuantity()/specialPrice.getQuantity())*specialPrice.getValue();
                    result += (counter.getQuantity()/specialPrice.getQuantity())*specialPrice.getValue();
                }
            }
        });*/
    }
}
