package by.itechart.flowerty.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.Set;

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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ADDRESS_ID")
    public Address getAddress() {
	return address;
    }

    public void setAddress(Address address) {
	this.address = address;
    }
    
    @OneToMany(mappedBy = "contact", fetch = FetchType.EAGER)
    public Set<Phone> getPhones() {
	return phones;
    }

    public void setPhones(Set<Phone> phones) {
	this.phones = phones;
    }
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="COMPANY_ID")
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    /*
		private Long id;
    private String name;
    private String surname;
    private String fathername;
    private Date birthday;
    private String email;
    private Address address;
    private Set<Phone> phones;
    private Company company;

     */
    
    
	@Override
	public String toString() {
		return new StringBuilder()
			.append("[id:").append(id)
			.append("\n name:").append(name)
			.append("\n surname:").append(surname)
			.append("\n fathername:").append(fathername)
			.append("\n birthday:").append(birthday)
			.append("\n email:").append(email)
			.append("\n address:").append(address).append("]\n")
//			.append("; phones:").append(phones)
			.toString();
	}
}
