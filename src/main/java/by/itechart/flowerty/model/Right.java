package by.itechart.flowerty.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "RIGHT")
public class Right {
    public static enum RIGHT_TYPE {
	CREATE_ORDER, 
	CREATE_CONTACT, 
	EDIT_CONTACT, 
	SEARCH_CONTACT, 
	VIEW_ORDERS, 
	COMMENT_ORDER, 
	SETTINGS, 
	CREATE_USER,
	EDIT_USER, 
	DELETE_USER,
	ASSIGN_ROLE
    }
    
    @Id
    @Column(name = "ID", length = 10, nullable = false)
    private Long id;
    
    @Column(name = "NAME", length = 20, nullable = false)
    private RIGHT_TYPE name;

    @ManyToMany(mappedBy = "rights")
    private Set<Role> employees = new HashSet<Role>();

    public Right() {
    }

    public Right(Long id, RIGHT_TYPE name, Set<Role> employees) {
	super();
	this.id = id;
	this.name = name;
	this.employees = employees;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public RIGHT_TYPE getName() {
	return name;
    }

    public void setName(RIGHT_TYPE name) {
	this.name = name;
    }

    public Set<Role> getEmployees() {
	return employees;
    }

    public void setEmployees(Set<Role> employees) {
	this.employees = employees;
    }
}
