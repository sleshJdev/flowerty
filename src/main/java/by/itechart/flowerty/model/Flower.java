package by.itechart.flowerty.model;

import javax.persistence.*;
<<<<<<< HEAD
=======
import javax.validation.constraints.Size;
>>>>>>> c1a9d88e855a73a46f665e7b6d057e3973267285

/**
 * User: Мария
 * Date: 21.03.15
 */
@Entity
@Table(name = "flower")
public class Flower {
    private Long id;
    private String name;
<<<<<<< HEAD
    private Integer remain;
    private Company company;
=======
>>>>>>> c1a9d88e855a73a46f665e7b6d057e3973267285

    public Flower() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }
<<<<<<< HEAD
    @Column(name = "COST")
    public Double getCost() {
        return cost;
    }
    @Column(name = "NAME", length = 20, nullable = false)
    public String getName() {
        return name;
    }
    @Column(name = "REMAIN")
    public Integer getRemain() {
        return remain;
    }
    @ManyToOne
    @JoinColumn(name="COMPANY_ID")
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
=======
    @Column(name = "NAME", length = 20, nullable = false)
    @Size(max=20)
    public String getName() {
        return name;
    }
>>>>>>> c1a9d88e855a73a46f665e7b6d057e3973267285
    public void setId(Long id) {
        this.id = id;
    }

<<<<<<< HEAD
    public void setCost(Double cost) {
        this.cost = cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRemain(Integer remain) {
        this.remain = remain;
    }
=======
    public void setName(String name) {
        this.name = name;
    }
>>>>>>> c1a9d88e855a73a46f665e7b6d057e3973267285

}
