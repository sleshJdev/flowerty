package test.by.itechart.flowerty.persistence.repository;

import by.itechart.flowerty.persistence.model.Goods;
import by.itechart.flowerty.persistence.repository.GoodsRepository;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.by.itechart.flowerty.config.aware.JpaConfigurationAware;
import test.by.itechart.flowerty.persistence.repository.helper.GoodsRepositoryHelperTest;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by Rostislav on 16-May-15
 */

public class GoodsRepositoryTest extends JpaConfigurationAware {

    @Autowired
    private GoodsRepository goodsRepository;

    @Test
    public void findOne_ShouldReturnAGoods() {

        Goods expected = GoodsRepositoryHelperTest.getGoodsWithIdOne();

        Goods actual = goodsRepository.findOne(1L);

        assertNotNull(actual);
        assertThat(actual, allOf(
                hasProperty("id", is(expected.getId())),
                hasProperty("cost", is(expected.getCost())),
                hasProperty("remain", is(expected.getRemain())),
                hasProperty("company", is(expected.getCompany())),
                hasProperty("image", is(expected.getImage()))
        ));
        assertThat(actual.getFlower(), allOf(
                hasProperty("id", is(expected.getFlower().getId())),
                hasProperty("name", is(expected.getFlower().getName()))
        ));
    }
}
