package by.itechart.flowerty.model;

import javax.persistence.*;

@Entity
@Table(name = "right")
public class Right {

    private Long id;
    private RIGHT_TYPE name;
    @Id
    @Column(name="ID", length=10, nullable=false)
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

    public static enum RIGHT_TYPE{
        CREATE_ORDER,
        CREATE_CONTACT,
        EDIT_CONTACT,
        SEARCH_CONTACT,
        VIEW_ORDERS,
        COMMENT_ORDER,
        SETTINGS,
        CREATE_USER,
        EDIT_USER,
        DELETE_USER, 
        ASSIGN_ROLE
    }
}
