package by.itechart.flowerty.model;


import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
    }
}
