package by.itechart.flowerty.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="RIGHT")
public class Right {

    private Long id;
    private RIGHT_TYPE name;
    private Set<Right> rights = new HashSet<Right>();

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
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="role_right",
            joinColumns={@JoinColumn(name="ROLE_ID")},
            inverseJoinColumns={@JoinColumn(name="RIGHT_ID")})
    public Set<Right> getRights() {
        return rights;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(RIGHT_TYPE name) {
        this.name = name;
    }


    public void setRights(Set<Right> rights) {
        this.rights = rights;
    }

    public Set<Role> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Role> employees) {
        this.employees = employees;
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
        DELETE_USER, ASSIGN_ROLE
    }

        @ManyToMany(mappedBy="rights")
        private Set<Role> employees = new HashSet<Role>();
}
