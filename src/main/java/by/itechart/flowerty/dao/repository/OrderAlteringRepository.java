package by.itechart.flowerty.dao.repository;

import by.itechart.flowerty.model.OrderAltering;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 27.03.15
 * Time: 6:55
 * To change this template use File | Settings | File Templates.
 */
@Transactional
public interface OrderAlteringRepository extends CrudRepository<OrderAltering, Long> {

}
