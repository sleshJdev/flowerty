package by.itechart.flowerty.model;

<<<<<<< HEAD
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import java.util.Calendar;
=======
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
>>>>>>> c1a9d88e855a73a46f665e7b6d057e3973267285
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
<<<<<<< HEAD
=======
    @Valid
>>>>>>> c1a9d88e855a73a46f665e7b6d057e3973267285
    public Order getOrder() {
        return order;
    }

    @ManyToOne
    @JoinColumn(name = "USER_ID")
<<<<<<< HEAD
=======
    @Valid
>>>>>>> c1a9d88e855a73a46f665e7b6d057e3973267285
    public User getUser() {
        return user;
    }
    @ManyToOne
    @JoinColumn(name = "STATE_ID")
<<<<<<< HEAD
=======
    @Valid
>>>>>>> c1a9d88e855a73a46f665e7b6d057e3973267285
    public State getState() {
        return state;
    }
    @Column(name = "DATE")
    @Temporal(value = TemporalType.DATE)
<<<<<<< HEAD
=======
    @Past
>>>>>>> c1a9d88e855a73a46f665e7b6d057e3973267285
    public Date getDate() {
        return date;
    }
    @Column(name = "COMMENT")
<<<<<<< HEAD
=======
    @Size(max=100)
>>>>>>> c1a9d88e855a73a46f665e7b6d057e3973267285
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
