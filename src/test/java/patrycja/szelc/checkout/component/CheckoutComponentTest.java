package patrycja.szelc.checkout.component;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import patrycja.szelc.checkout.component.model.Item;
import patrycja.szelc.checkout.component.model.SpecialPrice;
import patrycja.szelc.checkout.component.repository.ItemRepository;
import patrycja.szelc.checkout.component.service.CheckoutService;
import patrycja.szelc.checkout.component.service.PriceCalculator;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
//@ContextConfiguration(classes = { TestConfig.class })
public class CheckoutComponentTest {


    @Autowired
    private ItemRepository itemRepository;


    @Bean
    public CheckoutService checkoutService() {
        return new CheckoutService(priceCalculator());
    }

    @Bean
    public PriceCalculator priceCalculator() {
        return new PriceCalculator(itemRepository);
    }

    @Autowired
    private TestEntityManager entityManager;

/*    @Autowired
    private CheckoutService checkoutService;*/

    @Before
    public void setUpDataBase() {
        Item A = new Item("A", 40, new SpecialPrice(3, 70));
        Item B = new Item("B", 10, new SpecialPrice(2, 15));
        Item C = new Item("C", 30, new SpecialPrice());
        Item D = new Item("D", 25, new SpecialPrice());
        entityManager.persist(A);
        entityManager.persist(B);
        entityManager.persist(C);
        entityManager.persist(D);
        entityManager.flush();
    }

    @Test
    public void add1FromEachOne_thenReturn105() {


        List<Item> found = itemRepository.findAll();

        System.out.print(found);

        // then
        assertThat(found.size())
                .isEqualTo(4);

/*        checkoutService.addItem("A");
        checkoutService.addItem("B");
        checkoutService.addItem("C");
        checkoutService.addItem("D");

        assertThat(checkoutService.checkoutTotalPrice()).isEqualTo(105);*/

    }


/*    @Test
    public void add2AAndB_thenReturn105() { }

    @Test
    public void add3AAndB_thenReturn105() { }

    @Test
    public void add7AAndB_thenReturn105() { }*/
}
