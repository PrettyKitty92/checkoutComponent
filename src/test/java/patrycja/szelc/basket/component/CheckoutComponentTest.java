package patrycja.szelc.basket.component;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import patrycja.szelc.basket.component.model.Item;
import patrycja.szelc.basket.component.model.SpecialPrice;
import patrycja.szelc.basket.component.repository.ItemRepository;
import patrycja.szelc.basket.component.service.BasketComponentService;
import patrycja.szelc.basket.component.service.PriceCalculator;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CheckoutComponentTest {


    @TestConfiguration
    static class CheckoutComponentTestContextConfiguration {

        @Autowired
        private ItemRepository itemRepository;

        @Bean
        public BasketComponentService checkoutService() {
            return new BasketComponentService(priceCalculator());
        }

        @Bean
        public PriceCalculator priceCalculator() {
            return new PriceCalculator(itemRepository);
        }
    }


    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BasketComponentService basketComponentService;

    @Before
    public void setUpDataBase() {
        Item A = new Item("A", 40, new SpecialPrice(3, 70));
        Item B = new Item("B", 10, new SpecialPrice(2, 15));
        Item C = new Item("C", 30, null);
        Item D = new Item("D", 25, null);
        entityManager.persist(A);
        entityManager.persist(B);
        entityManager.persist(C);
        entityManager.persist(D);
        entityManager.flush();

        basketComponentService.clear();
    }

    @Test
    public void add1FromEachOne_thenReturn105() {

        addItems(Arrays.asList(new String[]{"A","B","C","D"}));

        assertThat(basketComponentService.checkoutTotalPrice()).isEqualTo(105);

    }


    @Test
    public void add2AAndB_thenReturn95() {

        addItems(Arrays.asList(new String[]{"A","A","B","B"}));

        assertThat(basketComponentService.checkoutTotalPrice()).isEqualTo(95);

    }

    @Test
    public void add3AAndB_thenReturn95() {

        addItems(Arrays.asList(new String[]{"A","A","A","B","B","B"}));

        assertThat(basketComponentService.checkoutTotalPrice()).isEqualTo(95);

    }

    @Test
    public void add7AAndB_thenReturn235() {

        addItems(Arrays.asList(new String[]{"A","A","A","A","A","A","A","B","B","B","B","B","B","B"}));

        assertThat(basketComponentService.checkoutTotalPrice()).isEqualTo(235);

    }

    private void addItems(List<String> items) {

        for(String item : items){
            basketComponentService.addItem(item);
        }
    }


}
