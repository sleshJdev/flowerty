package by.itechart.flowerty.dao.repository;

import by.itechart.flowerty.model.Contact;
import by.itechart.flowerty.model.Order;
import by.itechart.flowerty.model.State;
import by.itechart.flowerty.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
    public Order findOne(Long id);
    public Order save(Order order);
    public void delete (Order order);
    public Page<Order> findByDelivery(User deliveryManager, Pageable pageable);
    public Page<Order> findByStaff(User staff, Pageable pageable);
    public Page<Order> findByCustomer(Contact customer, Pageable pageable);
    public Page<Order> findByState(State state, Pageable pageable);
   // Page<Order> findAll(Pageable pageable);
}
