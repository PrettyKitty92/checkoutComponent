package patrycja.szelc.checkout.component;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import patrycja.szelc.checkout.component.repository.ItemRepository;

@Configuration
public class TestConfig {

    @Autowired
    private ItemRepository itemRepository;


}
