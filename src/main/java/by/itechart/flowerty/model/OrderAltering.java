package by.itechart.flowerty.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 20.03.15
 * Time: 22:41
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "order_altering")
public class OrderAltering {
    private Long id;
    private Order order;
    private User user;
    private State state;
    private Date date;
    private String comment;

    public OrderAltering() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", length = 10, nullable = false)
    public Long getId() {
        return id;
    }
    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    public Order getOrder() {
        return order;
    }

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    public User getUser() {
        return user;
    }
    @ManyToOne
    @JoinColumn(name = "STATE_ID")
    public State getState() {
        return state;
    }
    @Column(name = "DATE")
    @Temporal(value = TemporalType.DATE)
    public Date getDate() {
        return date;
    }
    @Column(name = "COMMENT")
    public String getComment() {
        return comment;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}
