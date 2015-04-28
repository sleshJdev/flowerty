package by.itechart.flowerty.model;

<<<<<<< HEAD

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 23.03.15
 * Time: 23:20
 * To change this template use File | Settings | File Templates.
=======
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * User: Мария Date: 23.03.15
>>>>>>> c1a9d88e855a73a46f665e7b6d057e3973267285
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
<<<<<<< HEAD
        this.website = website;
        this.name = name;
        this.id = id;
    }
=======
	this.id = id;
	this.name = name;
	this.website = website;
    }

>>>>>>> c1a9d88e855a73a46f665e7b6d057e3973267285
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
<<<<<<< HEAD
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
=======
	return id;
    }

    @Column(name = "NAME", length = 20)
    @Size(max = 100)
    public String getName() {
	return name;
    }
    @Column(name = "WEBSITE", length = 100)
    @Size(max = 100)
    @Pattern(regexp = "/^(https?:\\/\\/)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([\\/\\w \\.-]*)*\\/?$/")

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
>>>>>>> c1a9d88e855a73a46f665e7b6d057e3973267285
    }
}
