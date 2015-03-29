package by.itechart.flowerty.model;


import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 23.03.15
 * Time: 23:20
 * To change this template use File | Settings | File Templates.
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
        this.website = website;
        this.name = name;
        this.id = id;
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
