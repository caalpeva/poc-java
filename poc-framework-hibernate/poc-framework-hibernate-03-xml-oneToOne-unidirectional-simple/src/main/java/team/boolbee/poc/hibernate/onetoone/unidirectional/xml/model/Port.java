package team.boolbee.poc.hibernate.onetoone.unidirectional.xml.model;

public class Port {
	
	private Long id;
	private Integer number;
	private PortType type;
	private Service service;

	/**
	 * default constructor
	 */
	public Port () {
	}

	/**
	 * @param id
	 * @param number
	 * @param type
	 * @param service
	 */
	public Port(Long id, Integer number, PortType type, Service service) {
		this.id = id;
		this.number = number;
		this.type = type;
		this.service = service;
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

	/**
	 * @return the type
	 */
	public PortType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(PortType type) {
		this.type = type;
	}

	/**
	 * @return the service
	 */
	public Service getService() {
		return service;
	}

	/**
	 * @param service the service to set
	 */
	public void setService(Service service) {
		this.service = service;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Port [id=" + id + ", number=" + number + ", type=" + type
				+ ", service=" + service + "]";
	}
}