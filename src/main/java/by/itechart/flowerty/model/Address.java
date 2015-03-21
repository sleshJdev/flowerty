package by.itechart.flowerty.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ADDRESS")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "TOWN", length = 20, nullable = true)
    private String town;
    
    @Column(name = "STREET", length = 20, nullable = true)
    private String street;
    
    @Column(name = "HOUSE", length = 20, nullable = true)
    private String house;
    
    @Column(name = "FLAT", length = 10, nullable = true)
    private String flat;
    
    @OneToMany(mappedBy = "address")
    private List<Contact> contacts;

    public Address() {
    }

    public Address(Long id, String town, String street, String house, String flat, List<Contact> contacts) {
	this.id = id;
	this.town = town;
	this.street = street;
	this.house = house;
	this.flat = flat;
	this.contacts = contacts;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getTown() {
	return town;
    }

    public void setTown(String town) {
	this.town = town;
    }

    public String getStreet() {
	return street;
    }

    public void setStreet(String street) {
	this.street = street;
    }

    public String getHouse() {
	return house;
    }

    public void setHouse(String house) {
	this.house = house;
    }

    public String getFlat() {
	return flat;
    }

    public void setFlat(String flat) {
	this.flat = flat;
    }

    public List<Contact> getContacts() {
	return contacts;
    }

    public void setContacts(List<Contact> contacts) {
	this.contacts = contacts;
    }
}
