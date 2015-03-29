package by.itechart.flowerty.model;

import javax.persistence.*;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "contact")
public class Contact {

	private Long id;
	private String name;
	private String surname;
	private String fathername;
	private Date birthday;
	private String email;
	private Address address;
	private Set<Phone> phones;
	private Company company;

	@OneToOne(mappedBy = "contact")
	private User user;

	public Contact() {
	}

	public Contact(Long id, String name, String surname, String fathername, Date birthday, String email,
			Address address, Company company) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.fathername = fathername;
		this.birthday = birthday;
		this.email = email;
		this.address = address;
		this.company = company;

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "NAME", length = 20, nullable = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "SURNAME", length = 20, nullable = true)
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Column(name = "FATHERNAME", length = 20, nullable = true)
	public String getFathername() {
		return fathername;
	}

	public void setFathername(String fathername) {
		this.fathername = fathername;
	}

	@Column(name = "BIRTHDAY", nullable = true)
	@Temporal(value = TemporalType.DATE)
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "EMAIL", length = 50, nullable = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ADDRESS_ID")
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "contact", fetch = FetchType.LAZY)
	public Set<Phone> getPhones() {
		return phones;
	}

	public void setPhones(Set<Phone> phones) {
		this.phones = phones;
	}

	@ManyToOne
	@JoinColumn(name = "COMPANY_ID")
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}
