package by.itechart.flowerty.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * User: Мария Date: 21.03.15
 * 
 */
@Entity
@Table(name = "item")
public class Item {
    private Long id;
    private int quantity;
    private Goods flower;

    public Item() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }
    @Column(name = "QUANTITY", nullable=false)
    @NotNull
    public int getQuantity() {
        return quantity;
    }
    @ManyToOne
    @JoinColumn(name = "FLOWER_ID")
    @Valid
    public Goods getFlower() {
        return flower;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setFlower(Goods flower) {
        this.flower = flower;
    }
}
