package by.itecharty.flowerty.dao.repository;

import by.itechart.flowerty.dao.repository.OrderRepository;
import by.itecharty.flowerty.config.JpaConfigurationAware;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 27.03.15
 * Time: 14:52
 * To change this template use File | Settings | File Templates.
 */
@Ignore
public class TestOrderRepository extends JpaConfigurationAware {
    @Autowired
    private OrderRepository orderRepository;


}
