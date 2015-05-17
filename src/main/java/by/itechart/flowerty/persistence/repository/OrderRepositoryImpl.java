package by.itechart.flowerty.persistence.repository;

import by.itechart.flowerty.persistence.model.Order;
import by.itechart.flowerty.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Maria
 *         Date: 16.05.15
 */
public class OrderRepositoryImpl extends QueryDslRepositorySupport implements OrderRepositoryCustom {
    //private static final QOrder ORDER = QOrder.order;

    public OrderRepositoryImpl() {
        super(Order.class);
    }

    @Autowired
    private EntityManager entityManager;


    @Override
    public List<Order> findAvailableByDelivery(User delivery) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Order> findAvailableByStaff(User staff) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
