package by.itecharty.flowerty.dao.repository;

import by.itechart.flowerty.dao.repository.OrderAlteringRepository;
import by.itechart.flowerty.model.Order;
import by.itechart.flowerty.model.OrderAltering;
import by.itechart.flowerty.model.State;
import by.itechart.flowerty.model.User;
import by.itecharty.flowerty.config.JpaConfigurationAware;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 30.03.15
 * Time: 9:14
 * To change this template use File | Settings | File Templates.
 */
public class TestOrderAlteringRepository extends JpaConfigurationAware {
    @Autowired
    private OrderAlteringRepository orderAlteringRepository;

    @Test
    public void save() {
        OrderAltering orderAltering = new OrderAltering();
        Order order = new Order();
        order.setId(1l);
        orderAltering.setDate(new Date());
        orderAltering.setOrder(order);
        State state = new State();
        state.setId(1l);
        orderAltering.setState(state);
        User user = new User();
        user.setId(1l);
        orderAltering.setUser(user);
        orderAltering.setComment("comment");
        orderAltering = orderAlteringRepository.save(orderAltering);
        Assert.assertEquals(orderAltering.getState(), state);
    }

}
