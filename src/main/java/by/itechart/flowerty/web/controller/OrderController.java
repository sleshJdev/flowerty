package by.itechart.flowerty.web.controller;

import by.itechart.flowerty.model.Order;
import by.itechart.flowerty.web.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Катерина on 24.04.2015.
 *
 * Controller for processing all the requests connected with orders
 */

@Controller
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "order/save", method = RequestMethod.POST)
    public Order save(@RequestBody Order orderToSave){
        LOGGER.info("Saving order: {}", orderToSave);
        Order savedOrder = orderService.save(orderToSave);
        return savedOrder;
    }
}
