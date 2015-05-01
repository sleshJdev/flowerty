package by.itechart.flowerty.persistence.repository;

import by.itechart.flowerty.config.JpaConfigurationAware;
import by.itechart.flowerty.persistence.model.Order;
import by.itechart.flowerty.persistence.model.OrderAltering;
import by.itechart.flowerty.persistence.model.State;
import by.itechart.flowerty.persistence.model.User;

import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;

/**
 @author  Мария 30.03.15
 */
public class TestOrderAlteringRepository extends JpaConfigurationAware {
    @Autowired
    private OrderAlteringRepository orderAlteringRepository;

    @Ignore
    @Test
    public void saveOrderAltering_ValidOrderAltering_ReturnsSameOrderAltering() {
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
    @Ignore
    @Test
    public void findOne_ValidId_ReturnsOrder() {
        OrderAltering orderAltering = orderAlteringRepository.findOne(1l);
        Assert.assertEquals((Object) orderAltering.getState().getId(), 1l);
        Assert.assertEquals(orderAltering.getUser().getContact().getName(), "Sergey");
        Assert.assertEquals(orderAltering.getComment(), "comment");
    }
    @Ignore
    @Test
    public void findOne_InvalidId_ReturnsNull() {
        OrderAltering orderAltering = orderAlteringRepository.findOne(1000l);
        Assert.assertNull(orderAltering);
    }
    @Ignore
    @Test
    public void findByOrder_CorrectOrder_ReturnsPageOfOrderAltering() {
        Order order = new Order();
        order.setId(1l);
        Page<OrderAltering> page = orderAlteringRepository.findByOrder(order, new PageRequest(0, 10));
        Assert.assertNotEquals(page.getContent().size(), 0);
        Assert.assertEquals(page.getContent().get(0).getComment(), "comment");
    }
    @Ignore
    @Test
    public void findByOrder_InvalidOrder_ReturnsEmptyPage() {
        Order order = new Order();
        order.setId(100l);
        Page<OrderAltering> page = orderAlteringRepository.findByOrder(order, new PageRequest(0, 10));
        Assert.assertEquals(page.getContent().size(), 0);
    }

}
