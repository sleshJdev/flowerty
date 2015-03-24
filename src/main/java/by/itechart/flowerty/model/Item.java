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
@Table(name = "item")
public class Item {
    private Long id;
    private int quantity;
    private Order order;
    private Flower flower;

    public Item() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }
    @Column(name = "QUANTITY", nullable=false)
    public int getQuantity() {
        return quantity;
    }
    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    public Order getOrder() {
        return order;
    }
    @ManyToOne
    @JoinColumn(name = "FLOWER_ID")
    public Flower getFlower() {
        return flower;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setFlower(Flower flower) {
        this.flower = flower;
    }
}
