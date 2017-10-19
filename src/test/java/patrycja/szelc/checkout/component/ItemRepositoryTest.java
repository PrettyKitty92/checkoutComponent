package patrycja.szelc.checkout.component;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import patrycja.szelc.checkout.component.model.Item;
import patrycja.szelc.checkout.component.model.SpecialPrice;
import patrycja.szelc.checkout.component.repository.ItemRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class ItemRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void whenFindAll_thenReturnAll() {
        // given
        Item A = new Item("A", 40, new SpecialPrice(3, 70));
        Item B = new Item("B", 10, new SpecialPrice(2, 15));
        Item C = new Item("C", 30, new SpecialPrice());
        Item D = new Item("D", 25, new SpecialPrice());
        entityManager.persist(A);
        entityManager.persist(B);
        entityManager.persist(C);
        entityManager.persist(D);
        entityManager.flush();

        // when
        List<Item> found = itemRepository.findAll();

        System.out.print(found);

        // then
        assertThat(found.size())
                .isEqualTo(4);
    }
}
