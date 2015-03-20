package by.itechart.flowerty.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="RIGHT")
public class Right {
    @Id
    @Column(name="ID", length=10, nullable=false)
    private Long id;
    
    @Column(name = "NAME", length = 20, nullable = false)
    private RIGHT_TYPE name;

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
        DELETE_USER, ASSIGN_ROLE}

        @ManyToMany(mappedBy="rights")
        private Set<Role> employees = new HashSet<Role>();
}
