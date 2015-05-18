package by.itechart.flowerty.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.solr.client.solrj.beans.Field;
/**
 * @author: Мария Date: 19.03.15
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
    @Field("LOGIN")
    @NotNull
    @Size(max=20)
    public String getLogin() {
        return login;
    }

    @Column(name = "PASSWORD", length = 100, nullable = false)
    @NotNull
    @Size(max=100)
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

    @Override
    public String toString() {
        return new StringBuilder("User{")
                .append("id=").append(id)
                .append(", login='").append(login).append('\'')
                .append(", password='").append(password + '\'')
                .append(", role=").append(role)
                .append(", contact=").append(contact)
                .append('}').toString();
    }
}
