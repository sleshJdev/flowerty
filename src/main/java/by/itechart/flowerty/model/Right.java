package by.itechart.flowerty.model;

import javax.persistence.*;

@Entity
@Table(name = "access")
public class Right {

    private Long id;
    private RIGHT_TYPE name;
<<<<<<< HEAD
    private Set<Right> rights = new HashSet<Right>();

    @Id
    @Column(name="ID", length=10, nullable=false)
    public Long getId() {
        return id;
    }

=======

    @Id
    @Column(name = "ID", length = 10, nullable = false)
    public Long getId() {
        return id;
    }
>>>>>>> c1a9d88e855a73a46f665e7b6d057e3973267285

    @Enumerated(value = EnumType.STRING)
    @Column(name = "NAME", length = 20, nullable = false)
    public RIGHT_TYPE getName() {
        return name;
    }
<<<<<<< HEAD
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
=======

    public void setId(Long id) {
        this.id = id;
>>>>>>> c1a9d88e855a73a46f665e7b6d057e3973267285
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
