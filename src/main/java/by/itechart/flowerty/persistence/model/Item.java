package by.itechart.flowerty.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * User: Мария Date: 21.03.15
 * 
 */
@Entity
@Table(name = "item")
public class Item {
    private Long id;
    private Integer quantity;
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
    public Integer getQuantity() {
        return quantity;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public Goods getGoods() {
        return goods;
    }

    @JsonIgnore
    @ManyToOne
    public Order getOrder() {
        return order;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return new StringBuilder("Item{")
                .append("id=").append(id)
                .append(", quantity=").append(quantity)
                .append(", goods=").append(goods)
                .append('}').toString();
    }
}
