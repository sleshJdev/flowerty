package by.itechart.flowerty.web.service;

import by.itechart.flowerty.dao.repository.OrderRepository;
import by.itechart.flowerty.dao.repository.StateRepository;
import by.itechart.flowerty.dao.repository.UserRepository;
import by.itechart.flowerty.model.Order;
import by.itechart.flowerty.model.State;
import by.itechart.flowerty.model.User;
import by.itechart.flowerty.web.model.OrderEditBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Катерина on 24.04.2015.
 *
 * Service for manipulating with orders
 */

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StateRepository stateRepository;

    @Transactional
    public Order save(Order orderToCreate){
        return orderRepository.save(orderToCreate);
    }

    public Page<Order> getPage(int page, int size){
        return orderRepository.findAll(new PageRequest(page, size));
    }

    public Order findOne(long id){
        return orderRepository.findOne(id);
    }

    public OrderEditBundle getOrderEditBundleById(long id, User editingUser){
        OrderEditBundle orderEditBundle = new OrderEditBundle();
        orderEditBundle.setOrder(findOne(id));
        List<State> allStates = (List<State>)stateRepository.findAll();
        //  Filter logic for this order and this user
        orderEditBundle.setAvailableStates(allStates);
        return orderEditBundle;
    }
}
