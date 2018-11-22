package team.boolbee.poc.hibernate.interceptors.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MissingDefaultConstructorObject implements Serializable {
	
	private static final long serialVersionUID = 3568438556896755359L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column
	String text;
	@Column
	Long value;

	// No default constructor.
	
	public MissingDefaultConstructorObject(String text, Long value) {
		setText(text);
		setValue(value);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "MissingDefaultConstructorObject{" + "id=" + id + ", text='" + text + '\'' + ", value=" + value + '}';
	}
}
