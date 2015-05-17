package test.by.itechart.flowerty.persistence.repository.helper;

import by.itechart.flowerty.persistence.model.Order;
import by.itechart.flowerty.persistence.model.State;
import by.itechart.flowerty.persistence.model.User;

/**
 * Created by Rostislav on 17-May-15
 */

public abstract class OrderRepositoryHelperTest {

    private static State getStateAccepted() {
        State state = new State();

        state.setId(1L);
        state.setDescription(State.DESCRIPTION_TYPE.ACCEPTED);

        return state;
    }

    private static User getStaff() {
        User user = new User();

        user.setId(6L);
        user.setLogin("orders_processor");
        user.setPassword("$2a$10$oD5eWiJRPeLAIaWxY6Caj.PK6tiaadUlCnuJdTxnqJl77VgHkgI6i");

        return user;
    }

    private static User getManager() {
        User user = new User();

        user.setId(5L);
        user.setLogin("orders_manager");
        user.setPassword("$2a$10$eQV7Z0TJFtNWR0NKjH7BIO5vIZll.UXZYeEMrkZHkUcVQzZaA7qJu");

        return user;
    }

    private static User getDelivery() {
        User user = new User();

        user.setId(4L);
        user.setLogin("delivery_manager");
        user.setPassword("$2a$10$Okl4EXDsJLi8grBfGVr0aeHz8jsO3VnQM.MJVr/2PMROPZOaTBnQG");

        return user;
    }

    public static Order getOrderWithIdOne() {
        Order order = new Order();

        order.setId(1L);
        order.setState(getStateAccepted());
        order.setCost(12.0);
        order.setCustomer(ContactRepositoryHelperTest.getContactWithIdOne());
        order.setReceiver(ContactRepositoryHelperTest.getContactWithIdOne());
        order.setStaff(getStaff());
        order.setManager(getManager());
        order.setDelivery(getDelivery());
        order.setDescription("15 Red roses bouquet");
        order.setAddress(null);

        return order;
    }
}
