package by.itechart.flowerty.persistence.model;

import by.itechart.flowerty.solr.model.OrderDocument;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

/**
 * User: Мария
 * Date: 20.03.15
 */
@Entity
@Table(name = "purchase")
public class Order {
    private Long id;
    private State state;
    private Double cost;
    private Contact customer;
    private Contact receiver;
    private User staff;
    private User manager;
    private User delivery;
    private String description;
    private Set<Item> items;
    private Date deliveryDate;
    private Address address;

    public Order() {
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
    @Valid
    public Contact getCustomer() {
        return customer;
    }
    @ManyToOne
    @JoinColumn(name = "RECEIVER_ID")
    @Valid
    public Contact getReceiver() {
        return receiver;
    }
    @ManyToOne
    @JoinColumn(name = "STAFF_ID")
    @Valid
    public User getStaff() {
        return staff;
    }
    @ManyToOne
    @JoinColumn(name = "MANAGER_ID")
    @Valid
    public User getManager() {
        return manager;
    }
    @ManyToOne
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

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade=CascadeType.PERSIST)
    public Set<Item> getItems() {
        return items;
    }

    @Column(name = "DELIVERY_DATE", nullable = true)
    @Temporal(value = TemporalType.DATE)
    public Date getDeliveryDate() {
        return deliveryDate;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ADDRESS_ID")
    @Valid
    public Address getAddress() {
        return address;
    }

    @JsonIgnore
    @Transient
    public OrderDocument getOrderDocument() {
        if(id == null || customer == null || receiver == null || deliveryDate == null){
            return null;
        }
        return new OrderDocument(id.toString(), customer.getFathername(), receiver.getFathername(), deliveryDate, customer.getCompany());
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

    public void setAddress(Address address) {
        this.address = address;
    }
}
