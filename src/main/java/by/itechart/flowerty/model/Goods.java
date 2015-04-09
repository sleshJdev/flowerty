package by.itechart.flowerty.model;

import javax.persistence.*;
import javax.validation.Valid;

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 24.03.15
 * Time: 21:03
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "goods")
public class Goods {
    private Long id;
    private Double cost;
    private Integer remain;
    private Company company;
    private Flower flower;
    public Goods() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }
    @Column(name = "COST")
    public Double getCost() {
        return cost;
    }
    @Column(name = "REMAIN")
    public Integer getRemain() {
        return remain;
    }
    @ManyToOne
    @JoinColumn(name="COMPANY_ID")
    @Valid
    public Company getCompany() {
        return company;
    }
    @ManyToOne
    @JoinColumn(name="FLOWER_ID")
    @Valid
    public Flower getFlower() {
        return flower;
    }

    public void setFlower(Flower flower) {
        this.flower = flower;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public void setRemain(Integer remain) {
        this.remain = remain;
    }

}
