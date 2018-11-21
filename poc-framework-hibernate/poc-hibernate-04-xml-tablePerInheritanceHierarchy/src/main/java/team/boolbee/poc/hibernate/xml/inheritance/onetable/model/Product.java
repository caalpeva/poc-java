package team.boolbee.poc.hibernate.xml.inheritance.onetable.model;

import java.io.Serializable;

public abstract class Product implements Serializable {
	
	private static final long serialVersionUID = 3384660249279744574L;
	
	private Long id;
	private String name;
	private Float price;
	
	/**
	 * default constructor
	 */
	public Product () {
	}

	/**
	 * @param name
	 * @param price
	 */
	public Product(String name, Float price) {
		this.name = name;
		this.price = price;
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
	 * @return the price
	 */
	public Float getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Float price) {
		this.price = price;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price
				+ "]";
	}
}
