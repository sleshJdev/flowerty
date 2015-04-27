package by.itechart.flowerty.web.service;

import by.itechart.flowerty.dao.repository.OrderRepository;
import by.itechart.flowerty.dao.repository.StateRepository;
import by.itechart.flowerty.dao.repository.UserRepository;
import by.itechart.flowerty.model.Order;
import by.itechart.flowerty.model.Role;
import by.itechart.flowerty.model.State;
import by.itechart.flowerty.model.User;
import by.itechart.flowerty.web.model.OrderEditBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        if(orderToCreate.getState().getDescription() == State.DESCRIPTION_TYPE.NEW){
            List<State> states = (List<State>)stateRepository.findAll();
        }
        return orderRepository.save(orderToCreate);
    }

    public Page<Order> getPage(int page, int size){
        return orderRepository.findAll(new PageRequest(page, size));
    }

    public Order findOne(long id){
        return orderRepository.findOne(id);
    }

    public OrderEditBundle getOrderEditBundleById(long id){
        List<State> availableStates = new ArrayList<State>();
        OrderEditBundle orderEditBundle = new OrderEditBundle();
        orderEditBundle.setOrder(findOne(id));

        //  Getting info about user, editing the order
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userPrincipal = null;
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            userPrincipal = (UserDetails)auth.getPrincipal();
            if(userPrincipal != null){
                Role editingUserRole = (Role)userPrincipal.getAuthorities().toArray()[0];
                //  Filter logic for this order and this user
                List<State> allStates = (List<State>)stateRepository.findAll();
                for(State state : allStates){
                    if(canChangeThisState(editingUserRole, state)){
                        availableStates.add(state);
                    }
                }
            }
        }
        orderEditBundle.setAvailableStates(availableStates);
        return orderEditBundle;
    }

    private boolean canChangeThisState(Role role, State state){
        switch (state.getDescription()){
            case CANCELED:{
                return true;
            }
            case IMPOSSIBLE:{
                return true;
            }
            case ACCEPTED:
            case PROCESSING:{
                return role.getName() == Role.ROLE_TYPE.ORDERS_PROCESSOR;
            }
            case READY:
            case DELIVERY:{
                return role.getName() == Role.ROLE_TYPE.DELIVERY_MANAGER;
            }
            default:{
                return false;
            }
        }
    }
}
