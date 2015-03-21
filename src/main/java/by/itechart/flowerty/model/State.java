package by.itechart.flowerty.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STATE")
public class State {
    public enum DESCRIPTION_TYPE{
	NEW,
	ACCEPTED,
	PROCESSING,
	READY,
	DELIVERY,
	IMOSSIBLE,
	CANCELED,
	CLOSE
    }
    
    @Id
    @Column(name = "ID", length = 10, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private byte id;

    @Column(name = "DESCRIPTION")
    private DESCRIPTION_TYPE description;

    public State() {
    }

    public State(byte id, DESCRIPTION_TYPE description) {
	super();
	this.id = id;
	this.description = description;
    }

    public byte getId() {
	return id;
    }

    public void setId(byte id) {
	this.id = id;
    }

    public DESCRIPTION_TYPE getDescription() {
	return description;
    }

    public void setDescription(DESCRIPTION_TYPE description) {
	this.description = description;
    }
}
