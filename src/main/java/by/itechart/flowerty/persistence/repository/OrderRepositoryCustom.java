package by.itechart.flowerty.persistence.repository;

import by.itechart.flowerty.persistence.model.Company;
import by.itechart.flowerty.persistence.model.Order;
import by.itechart.flowerty.persistence.model.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Maria
 *         Date: 16.05.15
 */
@NoRepositoryBean
public interface OrderRepositoryCustom {
    public Page<Order> findAvailableByDelivery(User delivery, Pageable pageable);

    public Page<Order> findAvailableByStaff(User staff, Pageable pageable);

    public Page<Order> findAvailableByOrdersManager(User staff, Pageable pageable);
    
    public Page<Order> findByCompany(Company company, Pageable pageable);
}
