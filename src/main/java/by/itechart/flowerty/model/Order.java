package by.itechart.flowerty.model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 20.03.15
 * Time: 22:40
 * To change this template use File | Settings | File Templates.
 */
public class Order {

    private State state;


    @ManyToOne
    @JoinColumn(name = "STATE_ID", nullable = false)
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }


}
