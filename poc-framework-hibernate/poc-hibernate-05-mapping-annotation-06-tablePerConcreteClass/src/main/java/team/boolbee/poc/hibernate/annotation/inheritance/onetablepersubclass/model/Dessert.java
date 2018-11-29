package team.boolbee.poc.hibernate.annotation.inheritance.onetablepersubclass.model;

import javax.persistence.Entity;

@Entity
public class Dessert extends Food {
	
	private static final long serialVersionUID = 2685675537632929074L;
	
	private Float calories;

	/**
	 * default constructor
	 */
	public Dessert () {
	}

	/**
	 * @param name
	 * @param price
	 * @param description
	 * @param calories
	 */
	public Dessert(String name, Float price, String description, Float calories) {
		super(name, price, description);
		this.calories = calories;
	}

	/**
	 * @return the calories
	 */
	public Float getCalories() {
		return calories;
	}

	/**
	 * @param calories the calories to set
	 */
	public void setCalories(Float calories) {
		this.calories = calories;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Dessert [calories=" + calories + ", toString()="
				+ super.toString() + "]";
	}
}
