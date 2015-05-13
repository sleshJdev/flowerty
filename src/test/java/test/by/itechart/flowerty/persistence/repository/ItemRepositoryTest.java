package test.by.itechart.flowerty.persistence.repository;

import by.itechart.flowerty.persistence.model.Item;
import by.itechart.flowerty.persistence.repository.ItemRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.by.itechart.flowerty.config.aware.JpaConfigurationAware;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by Rostislav on 13-May-15
 */
public class ItemRepositoryTest extends JpaConfigurationAware {

    @Autowired
    private ItemRepository itemRepository;

    @Ignore
    @Test
    public void findOne() {

        Item item = itemRepository.findOne(1L);

        assertThat(item.getId(), equalTo(1L));
    }
}
