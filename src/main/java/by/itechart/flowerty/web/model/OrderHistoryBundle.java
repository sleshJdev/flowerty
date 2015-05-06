package by.itechart.flowerty.web.model;

import by.itechart.flowerty.persistence.model.Order;
import by.itechart.flowerty.persistence.model.OrderAltering;

import java.util.List;

/**
 * Created by Катерина on 05.05.2015.
 */
public class OrderHistoryBundle {

    private Order order;

    private List<OrderAltering> orderAlterings;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderAltering> getOrderAlterings() {
        return orderAlterings;
    }

    public void setOrderAlterings(List<OrderAltering> orderAlterings) {
        this.orderAlterings = orderAlterings;
    }

    public OrderHistoryBundle(Order order, List<OrderAltering> orderAlterings) {
        this.order = order;
        this.orderAlterings = orderAlterings;
    }

    public OrderHistoryBundle() {
    }
}
