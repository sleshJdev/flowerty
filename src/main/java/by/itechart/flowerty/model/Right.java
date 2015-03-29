package by.itechart.flowerty.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "RIGHT")
public class Right {
    @Id
    @Column(name = "ID", length = 10, nullable = false)
    private Long id;

    @Enumerated(value = EnumType.STRING)
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
        DELETE_USER, 
        ASSIGN_ROLE
    }

    @ManyToMany(mappedBy = "rights")
    private Set<Role> employees = new HashSet<Role>();
}
