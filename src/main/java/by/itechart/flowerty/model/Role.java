package by.itechart.flowerty.model;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ROLE")
public class Role {
<<<<<<< HEAD

    private Long id;
    private ROLE_TYPE name;
    private Set<Right> rights = new HashSet<Right>();
    private Set<User> users = new HashSet<User>();
    public static enum ROLE_TYPE{
        ORDERS_MANAGER,
        ORDERS_PROCESSOR,
        DELIVERY_MANAGER,
        SUPERVISOR,
        ADMIN
    }

    public Role() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", length = 10, nullable = false)
    public Long getId() {
        return id;
    }
    @Enumerated(value = EnumType.STRING)
    @Column(name = "NAME", nullable = false)
    public ROLE_TYPE getName() {
        return name;
    }
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="role_right",
            joinColumns={@JoinColumn(name="RIGHT_ID")},
            inverseJoinColumns={@JoinColumn(name="ROLE_ID")})
    public Set<Right> getRights() {
        return rights;
    }
    @OneToMany(mappedBy = "role")
    public Set<User> getUsers() {
        return users;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(ROLE_TYPE name) {
        this.name = name;
    }

    public void setRights(Set<Right> rights) {
        this.rights = rights;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
=======
	private Long id;
	private ROLE_TYPE name;
	private Set<Right> rights = new HashSet<>();

	public static enum ROLE_TYPE {
		ORDERS_MANAGER, ORDERS_PROCESSOR, DELIVERY_MANAGER, SUPERVISOR, ADMIN
	}

	public Role() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", length = 10, nullable = false)
	public Long getId() {
		return id;
	}

	@Enumerated(value = EnumType.STRING)
	@Column(name = "NAME", nullable = false)
	public ROLE_TYPE getName() {
		return name;
	}

	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "role_right", 
				joinColumns = { @JoinColumn(name = "ROLE_ID") },
				inverseJoinColumns = { @JoinColumn(name = "RIGHT_ID") })
	public Set<Right> getRights() {
		return rights;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(ROLE_TYPE name) {
		this.name = name;
	}

	public void setRights(Set<Right> rights) {
		this.rights = rights;
	}
>>>>>>> c1a9d88e855a73a46f665e7b6d057e3973267285
}
