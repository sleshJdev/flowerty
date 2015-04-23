package by.itechart.flowerty.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "access")
public class Right {

    private Long id;
    private RIGHT_TYPE name;
    Set<Role> roles = new HashSet<>();

    public Right() {
    }

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

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "role_right",
            joinColumns = {@JoinColumn(name = "RIGHT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
    public Set<Role> getRoles() {
        return roles;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(RIGHT_TYPE name) {
        this.name = name;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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
