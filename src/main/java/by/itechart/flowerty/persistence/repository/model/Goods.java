package by.itechart.flowerty.persistence.repository.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Size;

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
    private String image;
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
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="FLOWER_ID")
    @Valid
    public Flower getFlower() {
        return flower;
    }
    @Column(name = "IMAGE_NAME")
    @Size(max = 255)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
