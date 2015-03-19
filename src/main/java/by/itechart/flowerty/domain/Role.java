package by.itechart.flowerty.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROLE")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", length = 10, nullable = false)
    private Long id;

    
    @Enumerated(value = EnumType.STRING)
    @Column(name = "NAME", nullable = false)
    private ROLE_TYPE name;
    
    public static enum ROLE_TYPE{
	CREATE_ORDER,
	CREATE_CONTACT,
	EDIT_CONTACT,
	SEARCH_CONTACT,
	VIEW_ORDERS,
	COMMENT_ORDER,
	SETTINGS,
	CREATE_USER,
	EDIT_USER,
	DELETE_USER, ASSIGN_ROLE
    }
}
