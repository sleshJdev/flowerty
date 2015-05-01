package by.itechart.flowerty.persistence.model;

import javax.persistence.*;

@Entity
@Table(name = "access")
public class Right {

    private Long id;
    private RIGHT_TYPE name;

    @Id
    @Column(name = "ID", length = 10, nullable = false)
    public Long getId() {
        return id;
    }

    @Enumerated(value = EnumType.STRING)
    @Column(name = "NAME", length = 20, nullable = false)
    public RIGHT_TYPE getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(RIGHT_TYPE name) {
        this.name = name;
    }

    public static enum RIGHT_TYPE {
        create_order,
        contact,
        comment_order,
        settings,
        user,
        assign_role,
        orders_ready,
        orders_accepted,
        orders_all
    }
}
