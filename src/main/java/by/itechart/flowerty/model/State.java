package by.itechart.flowerty.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 20.03.15
 * Time: 22:41
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "state")
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", length = 10, nullable = false)
    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DESCRYPTION_TYPE getDescryption() {
        return descryption;
    }

    public void setDescryption(DESCRYPTION_TYPE descryption) {
        this.descryption = descryption;
    }

    @Enumerated(value = EnumType.STRING)
    @Column(name = "DESCRYPTION", nullable = false)
    private DESCRYPTION_TYPE descryption;

    public static enum DESCRYPTION_TYPE{
        ORDERS_MANAGER,
        ORDERS_PROCESSOR,
        DELIVERY_MANAGER,
        SUPERVISOR,
        ADMIN
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @OneToMany(mappedBy = "state")
    private Set<Order> orders;
}
