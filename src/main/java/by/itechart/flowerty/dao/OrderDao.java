package by.itechart.flowerty.dao;

import by.itechart.flowerty.model.Order;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 24.03.15
 * Time: 7:34
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface OrderDao {
    public void saveOrder(Order order);
}
