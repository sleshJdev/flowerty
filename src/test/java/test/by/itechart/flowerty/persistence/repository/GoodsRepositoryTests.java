package test.by.itechart.flowerty.persistence.repository;

import by.itechart.flowerty.persistence.model.Goods;
import by.itechart.flowerty.persistence.repository.GoodsRepository;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.by.itechart.flowerty.config.aware.JpaConfigurationAware;
import test.by.itechart.flowerty.persistence.repository.helper.GoodsRepositoryHelperTests;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by Rostislav on 16-May-15
 */

public class GoodsRepositoryTests extends JpaConfigurationAware {

    @Autowired
    private GoodsRepository goodsRepository;

    @Ignore
    @Test
    public void findOne_ShouldReturnAGoods() {

        Goods expected = GoodsRepositoryHelperTests.getGoodsWithIdOne();

        Goods actual = goodsRepository.findOne(1L);

        Assert.assertEquals(expected.getFlower().getId(), actual.getFlower().getId());
        Assert.assertEquals(expected.getFlower().getName(), actual.getFlower().getName());
        Assert.assertEquals(expected.getFlower().getClass(), actual.getFlower().getClass());
//        Assert.assertEquals(expected.getFlower(), actual.getFlower());

        assertNotNull(actual);
        assertThat(actual, allOf(
                hasProperty("id", is(expected.getId())),
                hasProperty("cost", is(expected.getCost())),
                hasProperty("remain", is(expected.getRemain())),
                hasProperty("company", is(expected.getCompany())),
//                hasProperty("flower", is(expected.getFlower())),
                hasProperty("image", is(expected.getImage()))
        ));
    }
}
