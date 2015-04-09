package by.itechart.flowerty.model;

import javax.persistence.*;

/**
 * User: Мария Date: 23.03.15
 */
@Entity
@Table(name = "company")
public class Company {
    private Long id;
    private String name;
    private String website;

    public Company() {
    }

    public Company(String website, String name, Long id) {
	this.id = id;
	this.name = name;
	this.website = website;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
	return id;
    }

    @Column(name = "NAME", length = 20)
    public String getName() {
	return name;
    }

    @Column(name = "WEBSITE", length = 20)
    public String getWebsite() {
	return website;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setWebsite(String website) {
	this.website = website;
    }
}
