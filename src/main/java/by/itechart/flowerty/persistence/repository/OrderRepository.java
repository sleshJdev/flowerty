package by.itechart.flowerty.persistence.repository;

import by.itechart.flowerty.persistence.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {
    public Order findOne(Long id);

 //   public Page<Order> findByDelivery(User deliveryManager, Pageable pageable);

//    public Page<Order> findByStaff(User staff, Pageable pageable);

    public Page<Order> findByCustomer(Contact customer, Pageable pageable);

    public Page<Order> findByState(State state, Pageable pageable);

    public Page<Order> findByIdIsIn(List<Long> list, Pageable pageable);
}
