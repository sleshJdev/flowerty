package by.itechart.flowerty.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * @author Maria
 * Date: 20.03.15
 */
@Entity
@Table(name = "phone")
public class Phone {
	private Long id;
//	private Contact contact;
	private String comment;
	private String country;
	private String number;
	private String operator;
	private PHONE_TYPE type;

	public Phone() {
	}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

//    @ManyToOne
//	@JoinColumn(name = "CONTACT_ID")
//    @Valid
//	public Contact getContact() {
//		return contact;
//	}


	@Column(name = "COUNTRY", length = 5, nullable = true)
	@Size(max=5)
    public String getCountry() {
		return country;
	}

	@Column(name = "NUMBER", length = 10, nullable = true)
    @Size(max=10)
	public String getNumber() {
		return number;
	}

	@Column(name = "OPERATOR", length = 5, nullable = true)
    @Size(max=5)
	public String getOperator() {
		return operator;
	}

	@Enumerated(value = EnumType.STRING)
	@Column(name = "TYPE", length = 20, nullable = false)
	public PHONE_TYPE getType() {
		return type;
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

//    public void setContact(Contact contact) {
//        this.contact = contact;
//    }

    public static enum PHONE_TYPE {
		HOME, CELL
	}
}
