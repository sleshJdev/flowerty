package by.itechart.flowerty.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {

	private Long id;
	private ROLE_TYPE name;
	private Set<Right> rights = new HashSet<Right>();

	public static enum ROLE_TYPE {
		ORDERS_MANAGER, 
		ORDERS_PROCESSOR, 
		DELIVERY_MANAGER,
		SUPERVISOR, ADMIN
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
		joinColumns = { @JoinColumn(name = "RIGHT_ID") }, 
		inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	public Set<Right> getRights() {
		return rights;
	}


//    @OneToMany(mappedBy = "role")
//    public Set<User> getUsers() {
//        return users;
//    }


	public void setId(Long id) {
		this.id = id;
	}

	public void setName(ROLE_TYPE name) {
		this.name = name;
	}

	public void setRights(Set<Right> rights) {
		this.rights = rights;
	}

}
