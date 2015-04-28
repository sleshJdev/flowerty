package by.itechart.flowerty.model;

import org.apache.solr.client.solrj.beans.Field;

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
<<<<<<< HEAD
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
=======
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
    @Field("LOGIN")
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
>>>>>>> c1a9d88e855a73a46f665e7b6d057e3973267285

    public void setId(Long id) {
        this.id = id;
    }
<<<<<<< HEAD
    @Column(name = "LOGIN", length = 20, nullable = false)
    public String getLogin() {
        return login;
    }
=======
>>>>>>> c1a9d88e855a73a46f665e7b6d057e3973267285

    public void setLogin(String login) {
        this.login = login;
    }
<<<<<<< HEAD
    @Column(name = "PASSWORD", length = 20, nullable = false)
    public String getPassword() {
        return password;
    }
=======
>>>>>>> c1a9d88e855a73a46f665e7b6d057e3973267285

    public void setPassword(String password) {
        this.password = password;
    }

<<<<<<< HEAD
    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    @OneToOne(mappedBy = "user")
    public Contact getContact() {
        return contact;
    }
=======
    public void setRole(Role role) {
        this.role = role;
    }
>>>>>>> c1a9d88e855a73a46f665e7b6d057e3973267285

    public void setContact(Contact contact) {
        this.contact = contact;
    }

<<<<<<< HEAD



=======
>>>>>>> c1a9d88e855a73a46f665e7b6d057e3973267285
}
