package by.itechart.flowerty.model;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "address")
public class Address {

    private Long id;
    private String town;
    private String street;
    private String house;
    private String flat;
    private String country;

    public Address() {
    }

    public Address(Long id, String town, String street, String house, String flat, String country) {
	super();
	this.id = id;
	this.town = town;
	this.street = street;
	this.house = house;
	this.flat = flat;
	this.country = country;
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
    @Column(name = "COUNTRY", length = 10, nullable = true)
    public String getCountry() {
        return country;

    }

    public void setCountry(String country) {
        this.country = country;
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
