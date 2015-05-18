package by.itechart.flowerty.persistence.repository;

import by.itechart.flowerty.persistence.model.Company;
import by.itechart.flowerty.persistence.model.Order;
import by.itechart.flowerty.persistence.model.QOrder;
import by.itechart.flowerty.persistence.model.State;
import by.itechart.flowerty.persistence.model.User;

import org.apache.tools.ant.types.CommandlineJava.SysProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import java.util.List;

/**
 * @author Maria
 *         Date: 16.05.15
 */
public class OrderRepositoryImpl extends QueryDslRepositorySupport implements OrderRepositoryCustom {
    private static final QOrder ORDER = QOrder.order;

    public OrderRepositoryImpl() {
        super(Order.class);
    }
    
    @Override
    public Page<Order> findByCompany(Company company, Pageable pageable) {
	System.out.println(company);
	List<Order> orders = 
		from(ORDER)
			.where(ORDER.receiver.company.eq(company))
		.list(ORDER);
	
	return new PageImpl<Order>(orders);
    }

    @Override
    public Page<Order> findAvailableByDelivery(User delivery, Pageable pageable) {
        List<Order> orders = 
        	from(ORDER)
        		.where(ORDER.delivery.eq(delivery)
        			.and((ORDER.state.description.eq(State.DESCRIPTION_TYPE.READY)
        				.or(ORDER.state.description.eq(State.DESCRIPTION_TYPE.DELIVERY)))))
        	.list(ORDER);
        
        return new PageImpl<Order>(orders, pageable, orders.size());
    }

    @Override
    public Page<Order> findAvailableByStaff(User staff, Pageable pageable) {
        List<Order> orders = 
        	from(ORDER)
        		.where(ORDER.staff.eq(staff)
        			.and((ORDER.state.description.eq(State.DESCRIPTION_TYPE.ACCEPTED)
        				.or(ORDER.state.description.eq(State.DESCRIPTION_TYPE.PROCESSING)))))
    		.list(ORDER);
        
        return new PageImpl<Order>(orders, pageable, orders.size());
    }

    @Override
    public Page<Order> findAvailableByOrdersManager(User ordersManager, Pageable pageable) {
        List<Order> orders = 
        	from(ORDER)
        		.where(ORDER.manager.eq(ordersManager)
        			.and(ORDER.state.description.eq(State.DESCRIPTION_TYPE.NEW)))
        	.list(ORDER);
        
        return new PageImpl<Order>(orders, pageable, orders.size());
    }
}
