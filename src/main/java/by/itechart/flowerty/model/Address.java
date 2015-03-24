package by.itechart.flowerty.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "ADDRESS")
public class Address {

    private Long id;
    private String town;
    private String street;
    private String house;
    private String flat;
    private Contact contact;

    public Address() {
    }

    public Address(Long id, String town, String street, String house, String flat, Contact contact) {
	super();
	this.id = id;
	this.town = town;
	this.street = street;
	this.house = house;
	this.flat = flat;
	this.contact = contact;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
	return id;
    }

    @Column(name = "TOWN", length = 20, nullable = true)
    public String getTown() {
	return town;
    }

    @Column(name = "STREET", length = 20, nullable = true)
    public String getStreet() {
	return street;
    }


    @Column(name = "HOUSE", length = 20, nullable = true)
    public String getHouse() {
	return house;
    }


    @Column(name = "FLAT", length = 10, nullable = true)
    public String getFlat() {
	return flat;
    }


    @OneToOne
    @PrimaryKeyJoinColumn(name = "CONTACT_ID")
    public Contact getContact() {
	return contact;
    }

    public void setContact(Contact contact) {
	this.contact = contact;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public void setHouse(String house) {
        this.house = house;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public void setTown(String town) {
        this.town = town;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
