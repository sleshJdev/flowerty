package by.itechart.flowerty.model;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created with IntelliJ IDEA. User: Мария Date: 19.03.15 Time: 22:29 To change
 * this template use File | Settings | File Templates.
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

    public void setId(Long id) {
        this.id = id;
    }
    @Column(name = "LOGIN", length = 20, nullable = false)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    @Column(name = "PASSWORD", length = 20, nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ROLE_ID")
	public Role getRole() {
		return role;
	}

    public void setRole(Role role) {
        this.role = role;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "CONTACT_ID")
	public Contact getContact() {
		return contact;
	}

	/*
		 private Long id;
    private String login;
    private String password;
    private Role role;
    private Contact contact;
	 *
	 */
	
	@Override
	public String toString() {
		return new StringBuilder()
			.append("[id:").append(id)
			.append("\n login:").append(login)
			.append("\n password:").append(password)
			.append("\n contact:").append(contact).append("]\n")
			.toString();
	}
}
