package by.itechart.flowerty.persistence.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Maria Date: 20.03.15
 */
@Entity
@Table(name = "phone")
public class Phone {
    private Long id;
    private String comment;
    private String country;
    private String number;
    private String operator;
    private Contact contact;
    private PHONE_TYPE type;
    
    public Phone() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
	return id;
    }

    @Column(name = "COMMENT", length = 50, nullable = true)
    @Size(max = 50)
    public String getComment() {
	return comment;
    }
    
    @Column(name = "COUNTRY", length = 5, nullable = true)
    @Size(max = 5)
    public String getCountry() {
	return country;
    }

    @Column(name = "NUMBER", length = 10, nullable = true)
    @Size(max = 10)
    public String getNumber() {
	return number;
    }

    @Column(name = "OPERATOR", length = 5, nullable = true)
    @Size(max = 5)
    public String getOperator() {
	return operator;
    }

    @Enumerated(value = EnumType.STRING)
    @Column(name = "TYPE", length = 20, nullable = false)
    public PHONE_TYPE getType() {
	return type;
    }
    
    @ManyToOne
    @JsonIgnore
    public Contact getContact() {
        return contact;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public void setComment(String comment) {
	this.comment = comment;
    }

    public void setCountry(String country) {
	this.country = country;
    }

    public void setNumber(String number) {
	this.number = number;
    }

    public void setOperator(String operator) {
	this.operator = operator;
    }

    public void setType(PHONE_TYPE type) {
	this.type = type;
    }
    
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public static enum PHONE_TYPE {
	HOME, CELL
    }
}
