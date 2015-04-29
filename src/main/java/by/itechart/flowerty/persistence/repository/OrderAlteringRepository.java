package by.itechart.flowerty.persistence.repository;

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 28.03.15
 * Time: 18:20
 * To change this template use File | Settings | File Templates.
 */

import by.itechart.flowerty.persistence.repository.model.Order;
import by.itechart.flowerty.persistence.repository.model.OrderAltering;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderAlteringRepository extends PagingAndSortingRepository<OrderAltering, Long> {
    public OrderAltering findOne(Long id);
    public OrderAltering save(OrderAltering orderAltering);
    public void delete (OrderAltering orderAltering);
    public Page<OrderAltering> findByOrder(Order order, Pageable pageable);
}
