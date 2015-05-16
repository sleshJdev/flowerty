package test.by.itechart.flowerty.persistence.repository;

import by.itechart.flowerty.persistence.model.Order;
import by.itechart.flowerty.persistence.repository.OrderRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.by.itechart.flowerty.config.aware.JpaConfigurationAware;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Rostislav on 16-May-15
 */

public class OrderRepositoryTests extends JpaConfigurationAware {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void filnOne_ShouldReturnAOrder() {

        Order actual = orderRepository.findOne(1L);

        assertNotNull(actual);
//        assertThat(actual, allOf(
//                hasProperty("id", is(expected.getId())),
//                hasProperty("cost", is(expected.getCost())),
//                hasProperty("remain", is(expected.getRemain())),
//                hasProperty("company", is(expected.getCompany())),
//                hasProperty("image", is(expected.getImage()))
//        ));

    }


}
