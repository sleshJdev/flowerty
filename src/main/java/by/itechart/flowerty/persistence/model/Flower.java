package by.itechart.flowerty.persistence.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * User: Мария
 * Date: 21.03.15
 */
@Entity
@Table(name = "flower")
public class Flower {
    private Long id;
    private String name;

    public Flower() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }
    @Column(name = "NAME", length = 20, nullable = false)
    @Size(max=20)
    public String getName() {
        return name;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringBuilder("Flower{")
                .append("id=").append(id)
                .append(", name='").append(name).append('\'')
                .append('}').toString();
    }
}
