package by.itechart.flowerty.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name="RIGHT")
public class Right {
    @Id
    @Column(name="ID", length=10, nullable=false)
    private Long id;
    
    @Column(name = "NAME", length = 20, nullable = false)
    private String name;
}
