package by.itechart.flowerty.persistence.repository;

import by.itechart.flowerty.persistence.model.Order;
import by.itechart.flowerty.persistence.model.User;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * @author Maria
 *         Date: 16.05.15
 */
@NoRepositoryBean
public interface OrderRepositoryCustom {
    public List<Order> findAvailableByDelivery(User delivery);

    public List<Order> findAvailableByStaff(User staff);

}
