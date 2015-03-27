package by.itechart.flowerty.dao.repository;

import by.itechart.flowerty.model.Order;
import by.itechart.flowerty.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface OrderRepository extends CrudRepository<Order, Long> {
    public Order findOne(Long id);
    public Order save(Order order);
    public void delete (Order order);
    public Order findByDelivery(User deliveryManager);
    public void findByStaff(User staff);

}
