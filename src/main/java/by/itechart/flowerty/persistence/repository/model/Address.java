package by.itechart.flowerty.persistence.repository.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

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
    // @NotNull
    // @Min(value = 1)
    public Long getId() {
	return id;
    }

    @Column(name = "TOWN", length = 100, nullable = true)
    @Size(max = 100)
    public String getTown() {
	return town;
    }

    @Size(max = 100)
    @Column(name = "STREET", length = 100, nullable = true)
    public String getStreet() {
	return street;
    }

    @Size(max = 20)
    @Column(name = "HOUSE", length = 20, nullable = true)
    public String getHouse() {
	return house;
    }

    @Size(max = 10)
    @Column(name = "FLAT", length = 10, nullable = true)
    public String getFlat() {
	return flat;
    }

    @Size(max = 100)
    @Column(name = "COUNTRY", length = 100, nullable = true)
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

    @Override
    public String toString() {
	return new StringBuffer().append("[id:").append(id).append(", town:").append(town).append(", street:")
		.append(street).append(", house:").append(house).append(", flat:").append(" country:").append(country)
		.append("]").toString();
    }

}
