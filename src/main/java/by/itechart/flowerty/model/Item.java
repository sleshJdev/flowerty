package by.itechart.flowerty.model;

import javax.persistence.*;
<<<<<<< HEAD
=======
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
>>>>>>> c1a9d88e855a73a46f665e7b6d057e3973267285

/**
 * User: Мария Date: 21.03.15
 * 
 */
@Entity
@Table(name = "item")
public class Item {
    private Long id;
    private int quantity;
    private Order order;
<<<<<<< HEAD
    private Flower flower;
=======
    private Goods flower;
>>>>>>> c1a9d88e855a73a46f665e7b6d057e3973267285

    public Item() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }
    @Column(name = "QUANTITY", nullable=false)
<<<<<<< HEAD
=======
    @NotNull
>>>>>>> c1a9d88e855a73a46f665e7b6d057e3973267285
    public int getQuantity() {
        return quantity;
    }
    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
<<<<<<< HEAD
=======
    @Valid
>>>>>>> c1a9d88e855a73a46f665e7b6d057e3973267285
    public Order getOrder() {
        return order;
    }
    @ManyToOne
    @JoinColumn(name = "FLOWER_ID")
<<<<<<< HEAD
    public Flower getFlower() {
=======
    @Valid
    public Goods getFlower() {
>>>>>>> c1a9d88e855a73a46f665e7b6d057e3973267285
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

<<<<<<< HEAD
    public void setFlower(Flower flower) {
=======
    public void setFlower(Goods flower) {
>>>>>>> c1a9d88e855a73a46f665e7b6d057e3973267285
        this.flower = flower;
    }
}
