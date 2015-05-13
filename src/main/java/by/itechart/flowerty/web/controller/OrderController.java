package by.itechart.flowerty.web.controller;

import by.itechart.flowerty.persistence.model.Order;
import by.itechart.flowerty.solr.model.OrderDocument;
import by.itechart.flowerty.web.model.OrderEditBundle;
import by.itechart.flowerty.web.service.OrderService;
import by.itechart.flowerty.web.model.OrderCreateBundle;
import by.itechart.flowerty.web.model.OrderHistoryBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Катерина on 24.04.2015.
 *
 * controller for processing all the requests connected with orders
 */
@Controller
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @ResponseBody
    @RequestMapping(value = "order/save", method = RequestMethod.POST)
    public Order save(@RequestBody Order orderToSave){
        LOGGER.info("Checkout order: {}", orderToSave);
        return orderService.save(orderToSave);
    }

    @ResponseBody
    @RequestMapping(value = "order/change/save", method = RequestMethod.POST)
    public Order saveChanges(@RequestBody OrderEditBundle orderEditBundle){
        LOGGER.info("saving changes in order {}", orderEditBundle.getOrder());
        return orderService.saveChanges(orderEditBundle);
    }

    @ResponseBody
    @RequestMapping(value = "order/list/{page}/{limit}", method = RequestMethod.GET)
    public Page<Order> page(@PathVariable("page") Integer page, @PathVariable("limit") Integer limit){
        LOGGER.info("Getting order page {} with limit {}", page, limit);

        page = (page == null || page < 1) ? 0 : --page;
        return orderService.getPage(page, limit);
    }

    @ResponseBody
    @RequestMapping(value = "order/details/{id}", method = RequestMethod.GET)
    public OrderEditBundle details(@PathVariable("id") Long id) throws Exception{
        LOGGER.info("get details about order with id: {}", id);

        if (id == null || id < 0) {
            throw new Exception("order id cannot be negative or null");
        }
        return orderService.getOrderEditBundleById(id);
    }

    @ResponseBody
    @RequestMapping(value = "order/search/{page}/{limit}", method = RequestMethod.POST)
    public Page<Order> search(@RequestBody OrderDocument order, @PathVariable("page") Integer page, @PathVariable("limit") Integer limit) {
        LOGGER.info("findBySearch - getting page {} with limit {}, order: {}", page, limit, order);

        page = (page == null || page < 1) ? 0 : --page;
        return  orderService.findBySearch(order, page, limit);
    }

    @ResponseBody
    @RequestMapping(value = "order/create/bundle", method = RequestMethod.GET)
    public OrderCreateBundle createBundle() throws Exception{
        LOGGER.info("getting prepared order bundle for creating a new one");
        return orderService.getOrderCreateBundle();
    }

    @ResponseBody
    @RequestMapping(value = "order/history/{id}", method = RequestMethod.GET)
    public OrderHistoryBundle history(@PathVariable("id") Long id){
        LOGGER.info("getting history for order with id = {}", id);
        return orderService.getOrderHistoryBundle(id);
    }
}
