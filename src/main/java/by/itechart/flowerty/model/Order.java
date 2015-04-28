package by.itechart.flowerty.model;

import javax.persistence.*;
<<<<<<< HEAD
=======
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;
>>>>>>> c1a9d88e855a73a46f665e7b6d057e3973267285

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 20.03.15
 * Time: 22:40
 * To change this template use File | Settings | File Templates.
 */
<<<<<<< HEAD
@Entity
@Table(name = "order")
=======

@Entity
@Table(name = "purchase")
>>>>>>> c1a9d88e855a73a46f665e7b6d057e3973267285
public class Order {
    private Long id;
    private State state;
    private Double cost;
    private Contact customer;
    private Contact receiver;
    private User staff;
    private User manager;
    private User delivery;
<<<<<<< HEAD

    public Order() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", length = 10, nullable = false)
    public Long getId() {
        return id;
=======
    private String description;
    private Set<Item> items;
    private Date deliveryDate;

    public Order() {
>>>>>>> c1a9d88e855a73a46f665e7b6d057e3973267285
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", length = 10, nullable = false)
    public Long getId() {
        return id;
    }
    
    @ManyToOne
    @JoinColumn(name = "STATE_ID")
    @Valid
    public State getState() {
        return state;
    }
    @Column(name = "COST")
    public Double getCost() {
        return cost;
    }
    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
<<<<<<< HEAD
=======
    @Valid
>>>>>>> c1a9d88e855a73a46f665e7b6d057e3973267285
    public Contact getCustomer() {
        return customer;
    }
    @ManyToOne
    @JoinColumn(name = "RECEIVER_ID")
<<<<<<< HEAD
=======
    @Valid
>>>>>>> c1a9d88e855a73a46f665e7b6d057e3973267285
    public Contact getReceiver() {
        return receiver;
    }
    @ManyToOne
    @JoinColumn(name = "STAFF_ID")
<<<<<<< HEAD
=======
    @Valid
>>>>>>> c1a9d88e855a73a46f665e7b6d057e3973267285
    public User getStaff() {
        return staff;
    }
    @ManyToOne
    @JoinColumn(name = "MANAGER_ID")
<<<<<<< HEAD
=======
    @Valid
>>>>>>> c1a9d88e855a73a46f665e7b6d057e3973267285
    public User getManager() {
        return manager;
    }
    @ManyToOne
<<<<<<< HEAD
    @JoinColumn(name = "DELIVERY_ID")
    public User getDelivery() {
        return delivery;
    }
=======
    @JoinColumn(name = "DELIVERY_MANAGER_ID")
    @Valid
    public User getDelivery() {
        return delivery;
    }
    @Column(name = "DESCRIPTION")
    @Size(max=300)
    public String getDescription() {
        return description;
    }
    @OneToMany(mappedBy = "order")
    public Set<Item> getItems() {
        return items;
    }

    @Column(name = "DELIVERY_DATE", nullable = true)
    @Temporal(value = TemporalType.DATE)
    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public void setDescription(String description) {
        this.description = description;
    }
>>>>>>> c1a9d88e855a73a46f665e7b6d057e3973267285

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
