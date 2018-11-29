package team.boolbee.poc.hibernate.xml.inheritance.onetablepersubclass.model;

public class Course extends Food {

	private static final long serialVersionUID = 8773720879804872101L;
	
	private String type;

	/**
	 * default constructor
	 */
	public Course () {
	}

	/**
	 * @param name
	 * @param price
	 * @param description
	 */
	public Course(String name, Float price, String description, String type) {
		super(name, price, description);
		this.type = type;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Course [type=" + type + ", toString()=" + super.toString()
				+ "]";
	}
}