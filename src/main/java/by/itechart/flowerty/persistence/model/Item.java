package by.itechart.flowerty.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private Goods goods;
    private Order order;

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
    @JoinColumn(name = "GOODS_ID")
    @Valid
    public Goods getGoods() {
        return goods;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @ManyToOne
    @JsonIgnore
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
