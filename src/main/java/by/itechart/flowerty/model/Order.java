package by.itechart.flowerty.model;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 20.03.15
 * Time: 22:40
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "order")
public class Order {
    private Long id;
    private State state;
    private Double cost;
    private Contact customer;
    private Contact receiver;
    private User staff;
    private User manager;
    private User delivery;

    public Order() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", length = 10, nullable = false)
    public Long getId() {
        return id;
    }
    
    @ManyToOne
    @JoinColumn(name = "STATE.ID")
    public State getState() {
        return state;
    }
    @Column(name = "COST")
    public Double getCost() {
        return cost;
    }
    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    public Contact getCustomer() {
        return customer;
    }
    @ManyToOne
    @JoinColumn(name = "RECEIVER_ID")
    public Contact getReceiver() {
        return receiver;
    }
    @ManyToOne
    @JoinColumn(name = "STAFF_ID")
    public User getStaff() {
        return staff;
    }
    @ManyToOne
    @JoinColumn(name = "MANAGER_ID")
    public User getManager() {
        return manager;
    }
    @ManyToOne
    @JoinColumn(name = "DELIVERY_ID")
    public User getDelivery() {
        return delivery;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public void setCustomer(Contact customer) {
        this.customer = customer;
    }

    public void setReceiver(Contact receiver) {
        this.receiver = receiver;
    }

    public void setStaff(User staff) {
        this.staff = staff;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public void setDelivery(User delivery) {
        this.delivery = delivery;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setState(State state) {
        this.state = state;
    }


}
