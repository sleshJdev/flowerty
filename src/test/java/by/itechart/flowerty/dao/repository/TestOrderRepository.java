package by.itechart.flowerty.dao.repository;

import by.itechart.flowerty.config.JpaConfigurationAware;
import by.itechart.flowerty.dao.repository.OrderRepository;
import by.itechart.flowerty.model.Contact;
import by.itechart.flowerty.model.Order;
import by.itechart.flowerty.model.State;
import by.itechart.flowerty.model.User;

import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
    @author Мария 27.03.15
 */
public class TestOrderRepository extends JpaConfigurationAware {
    @Autowired
    private OrderRepository orderRepository;

    @Ignore
    @Test
    public void saveOrder_ValidOrder_ReturnsSameOrder() {
        User user = new User();
        user.setId(1l);
        Contact contact = new Contact();
        contact.setId(1l);
        contact.setName("TestName");
        Order order = new Order();
        order.setCost(12.987);
        order.setCustomer(contact);
        order.setDelivery(user);
        order.setStaff(user);
        State state = new State();
        state.setId(2l);
        order.setState(state);
        order.setReceiver(contact);
        order.setManager(user);
        order.setDescription("WE");
        orderRepository.save(order);
    }
    @Ignore
    @Test
    public void findOne_ValidId_ReturnsOrder() {
        Order order = orderRepository.findOne(1l);
        Assert.assertEquals(order.getDescription(), "WE");
        Assert.assertEquals(order.getCustomer().getName(), "Anton");
    }
    @Ignore
    @Test
    public void findOne_InvalidId_ReturnsNull() {
        Order order = orderRepository.findOne(100l);
        Assert.assertNull(order);
    }
    @Ignore
    @Test
    public void findByCustomer_ValidId_ReturnsPageOfOrders() {
        Contact contact = new Contact();
        contact.setId(1l);
        Page<Order> page = orderRepository.findByCustomer(contact, new PageRequest(0, 10));
        Assert.assertEquals(page.getContent().get(0).getCustomer().getName(), "Anton");
    }

}
