package by.itechart.flowerty.model;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 20.03.15
 * Time: 22:41
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "phone")
public class Phone {
	private Long id;
	private Contact contact;
	private String comment;
	private String country;
	private String number;
	private String operator;
	private PHONE_TYPE type;

	public Phone() {
	}

	@ManyToOne
	@JoinColumn(name = "CONTACT_ID")
	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public String getComment() {
		return comment;
	}

	@Column(name = "COUNTRY", length = 5, nullable = true)
	public String getCountry() {
		return country;
	}

	@Column(name = "NUMBER", length = 10, nullable = true)
	public String getNumber() {
		return number;
	}

	@Column(name = "OPERATOR", length = 5, nullable = true)
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

	public static enum PHONE_TYPE {
		HOME, CELL
	}
}
