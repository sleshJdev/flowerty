package by.itechart.flowerty.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", length = 10, nullable = false)
    private Long id;

    
    @Enumerated(value = EnumType.STRING)
    @Column(name = "NAME", nullable = false)
    private ROLE_TYPE name;
    
    public static enum ROLE_TYPE{
        ORDERS_MANAGER,
        ORDERS_PROCESSOR,
        DELIVERY_MANAGER,
        SUPERVISOR,
        ADMIN
    }
        @ManyToMany(cascade = {CascadeType.ALL})
        @JoinTable(name="role_right",
                joinColumns={@JoinColumn(name="RIGHT_ID")},
                inverseJoinColumns={@JoinColumn(name="ROLE_ID")})
        private Set<Right> rights = new HashSet<Right>();

        @OneToMany(mappedBy = "role")
        private Set<User> users = new HashSet<User>();

}
