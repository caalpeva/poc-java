package team.boolbee.poc.hibernate.xml.inheritance.onetable.model;

public class Food extends Product {

	private static final long serialVersionUID = -1907792830533437146L;
	private String description;
	
	/**
	 * default constructor
	 */
	public Food () {
	}

	/**
	 * @param name
	 * @param price
	 * @param description
	 */
	public Food(String name, Float price, String description) {
		super(name, price);
		this.description = description;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Food [description=" + description + ", toString()="
				+ super.toString() + "]";
	}

}
