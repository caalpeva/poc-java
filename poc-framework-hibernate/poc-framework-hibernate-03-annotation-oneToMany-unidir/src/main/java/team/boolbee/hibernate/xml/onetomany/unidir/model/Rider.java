package team.boolbee.hibernate.xml.onetomany.unidir.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Rider {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Integer number;
	
	/**
	 * default constructor
	 */
	public Rider () {
	}
	
	/**
	 * @param id
	 * @param name
	 * @param number
	 */
	public Rider(Long id, String name, Integer number) {
		this.id = id;
		this.name = name;
		this.number = number;
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the number
	 */
	public Integer getNumber() {
		return number;
	}
	
	/**
	 * @param number the number to set
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Player [id=" + id + ", name=" + name + ", number=" + number
				+ "]";
	}
	
	

}
