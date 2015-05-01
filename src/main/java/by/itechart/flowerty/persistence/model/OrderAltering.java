package by.itechart.flowerty.persistence.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author Maria
 * Date: 20.03.15
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
    @Valid
    public Order getOrder() {
        return order;
    }

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    @Valid
    public User getUser() {
        return user;
    }
    @ManyToOne
    @JoinColumn(name = "STATE_ID")
    @Valid
    public State getState() {
        return state;
    }
    @Column(name = "DATE")
    @Temporal(value = TemporalType.DATE)
    @Past
    public Date getDate() {
        return date;
    }
    @Column(name = "COMMENT")
    @Size(max=100)
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
