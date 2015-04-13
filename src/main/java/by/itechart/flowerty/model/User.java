package by.itechart.flowerty.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * User: Мария Date: 19.03.15
 */
@Entity
@Table(name = "user")
public class User {
	public User() {
	}

	public User(Long id, String login, String password, Role role, Contact contact) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.role = role;
		this.contact = contact;
	}

	private Long id;
	private String login;
	private String password;
	private Role role;
	private Contact contact;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

    @Column(name = "LOGIN", length = 20, nullable = false)
    @NotNull
    @Size(max=20)
    public String getLogin() {
        return login;
    }


    @Column(name = "PASSWORD", length = 20, nullable = false)
    @NotNull
    @Size(max=20)
    public String getPassword() {
        return password;
    }


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ROLE_ID")
    @Valid
	public Role getRole() {
		return role;
	}


	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CONTACT_ID")
    @Valid
	public Contact getContact() {
		return contact;
	}

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

}
