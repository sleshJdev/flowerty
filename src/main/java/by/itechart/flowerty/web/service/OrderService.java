package by.itechart.flowerty.web.service;

import by.itechart.flowerty.dao.repository.OrderRepository;
import by.itechart.flowerty.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Катерина on 24.04.2015.
 *
 * Service for manipulating with orders
 */

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public Order save(Order orderToCreate){
        return orderRepository.save(orderToCreate);
    }
}
