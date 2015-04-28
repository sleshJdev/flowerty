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

    private Long id;
    private DESCRIPTION_TYPE description;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", length = 10, nullable = false)
    public Long getId() {
        return id;
    }

    @Enumerated(value = EnumType.STRING)
    @Column(name = "DESCRIPTION", nullable = false)
    public DESCRIPTION_TYPE getDescription() {
        return description;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setDescription(DESCRIPTION_TYPE description) {
        this.description = description;
    }



    public static enum DESCRIPTION_TYPE{
        ACCEPTED,
        CANCELED,
        CLOSED,
        DELIVERY,
        IMPOSSIBLE,
        NEW,
        PROCESSING,
        READY
    }
}
