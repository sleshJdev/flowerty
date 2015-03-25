package by.itechart.flowerty.model;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 21.03.15
 * Time: 19:58
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "flower")
public class Flower {
    private Long id;
    private String name;
<<<<<<< HEAD
=======
    private Integer remain;
    private Company company;
>>>>>>> a59ae5c2bd5443defc0eb1b548e51c77c211ae89

    public Flower() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }
<<<<<<< HEAD
=======
    @Column(name = "COST")
    public Double getCost() {
        return cost;
    }
>>>>>>> a59ae5c2bd5443defc0eb1b548e51c77c211ae89
    @Column(name = "NAME", length = 20, nullable = false)
    public String getName() {
        return name;
    }
<<<<<<< HEAD
=======
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
>>>>>>> a59ae5c2bd5443defc0eb1b548e51c77c211ae89
    public void setId(Long id) {
        this.id = id;
    }

<<<<<<< HEAD
    public void setName(String name) {
        this.name = name;
    }
=======
    public void setCost(Double cost) {
        this.cost = cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRemain(Integer remain) {
        this.remain = remain;
    }
>>>>>>> a59ae5c2bd5443defc0eb1b548e51c77c211ae89

}
