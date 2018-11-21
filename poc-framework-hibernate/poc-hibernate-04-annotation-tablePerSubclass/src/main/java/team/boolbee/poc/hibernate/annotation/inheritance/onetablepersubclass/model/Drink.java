package team.boolbee.poc.hibernate.annotation.inheritance.onetablepersubclass.model;

import javax.persistence.Entity;

@Entity
public class Drink extends Product {

	private static final long serialVersionUID = -1041109481450180546L;
	
	private Boolean isCold;

	/**
	 * default constructor
	 */
	public Drink() {
	}

	/**
	 * @param name
	 * @param price
	 * @param isCold
	 */
	public Drink(String name, Float price, Boolean isCold) {
		super( name, price);
		this.isCold = isCold;
	}

	/**
	 * @return the isCold
	 */
	public Boolean getIsCold() {
		return isCold;
	}

	/**
	 * @param isCold
	 *            the isCold to set
	 */
	public void setIsCold(Boolean isCold) {
		this.isCold = isCold;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Drink [isCold=" + isCold + ", toString()="
				+ super.toString() + "]";
	}
}
