package by.itechart.flowerty.dao.repository;

import org.springframework.data.repository.CrudRepository;

import by.itechart.flowerty.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
