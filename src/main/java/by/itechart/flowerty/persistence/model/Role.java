package by.itechart.flowerty.persistence.model;

import javax.persistence.*;

@Entity
@Table(name = "ROLE")
public class Role {
	private Long id;
	private ROLE_TYPE name;

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

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(ROLE_TYPE name) {
		this.name = name;
	}

    @Override
    public String toString() {
        return new StringBuilder("Role{")
                .append("id=").append(id)
                .append(", name=").append(name)
                .append('}').toString();
    }

}
