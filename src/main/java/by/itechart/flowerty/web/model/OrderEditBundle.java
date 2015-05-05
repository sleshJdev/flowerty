package by.itechart.flowerty.web.model;

import by.itechart.flowerty.persistence.model.Order;
import by.itechart.flowerty.persistence.model.OrderAltering;
import by.itechart.flowerty.persistence.model.State;

import java.util.List;

/**
 * Created by Катерина on 26.04.2015.
 */
public class OrderEditBundle {

    private Order order;

    private OrderAltering orderAltering;

    private List<State> availableStates;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<State> getAvailableStates() {
        return availableStates;
    }

    public void setAvailableStates(List<State> availableStates) {
        this.availableStates = availableStates;
    }

    public OrderEditBundle(Order order, List<State> availableStates, OrderAltering orderAltering) {
        this.order = order;
        this.availableStates = availableStates;
    }

    public OrderEditBundle() {
    }

    public OrderAltering getOrderAltering() {
        return orderAltering;
    }

    public void setOrderAltering(OrderAltering orderAltering) {
        this.orderAltering = orderAltering;
    }
}
