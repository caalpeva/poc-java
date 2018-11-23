package team.boolbee.poc.hibernate.interceptors.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MissingDefaultCtorObject implements Serializable {
	
	private static final long serialVersionUID = 3568438556896755359L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column
	String text;

	// No default constructor.
	
	public MissingDefaultCtorObject(String text) {
		setText(text);
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

	@Override
	public String toString() {
		return "MissingDefaultConstructorObject{" + "id=" + id + ", text='" + text + "\'}";
	}
}
