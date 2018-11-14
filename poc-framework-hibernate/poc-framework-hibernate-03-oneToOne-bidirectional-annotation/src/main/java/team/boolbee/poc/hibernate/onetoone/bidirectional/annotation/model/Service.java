package team.boolbee.poc.hibernate.onetoone.bidirectional.annotation.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Service {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String path;
	
	@OneToOne(cascade = {CascadeType.ALL})
	private Port port;

	/**
	 * default constructor
	 */
	public Service () {
	}

	/**
	 * @param id
	 * @param name
	 * @param path
	 * @param port
	 */
	public Service(Long id, String name, String path, Port port) {
		this.id = id;
		this.name = name;
		this.path = path;
		this.port = port;
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
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the port
	 */
	public Port getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(Port port) {
		this.port = port;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Service [id=" + id + ", name=" + name + ", path=" + path
				+ ", port=" + port + "]";
	}
	
	
}
