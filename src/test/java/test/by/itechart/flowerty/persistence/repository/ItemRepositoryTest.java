package test.by.itechart.flowerty.persistence.repository;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import test.by.itechart.flowerty.config.aware.JpaConfigurationAware;
import test.by.itechart.flowerty.persistence.repository.helper.ItemRepositoryHelperTest;
import by.itechart.flowerty.persistence.model.Item;
import by.itechart.flowerty.persistence.repository.ItemRepository;

/**
 * Created by Rostislav on 13-May-15
 */

public class ItemRepositoryTest extends JpaConfigurationAware {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void findOne_ShouldReturnAItem() {

        Item expected = ItemRepositoryHelperTest.getItemWithIdOne();

        Item actual = itemRepository.findOne(1L);

        Assert.assertNotNull(actual);
        assertThat(actual, allOf(
                hasProperty("id", is(expected.getId())),
                hasProperty("quantity", is(expected.getQuantity()))
        ));
    }
}
