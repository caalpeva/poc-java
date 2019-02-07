package team.boolbee.poc.spring.hibernate.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Vehicle implements Serializable {

	private static final long serialVersionUID = 1332582671884637738L;
	
	private Integer id;
	private String plateNumber;
	private VehicleType type;
	private Date registrationDate;
	private Person owner;
	
	public Vehicle() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = stripNonAlphanumeric(plateNumber);
	}
	
	public VehicleType getType() {
		return type;
	}

	public void setType(VehicleType type) {
		this.type = type;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

	private String stripNonAlphanumeric(String in) {
		if (in == null) {
			return null;
		}

		StringBuffer outBuffer = new StringBuffer(in.length());

		for (int i = 0; i < in.length(); i++) {
			char c = in.charAt(i);
			if (Character.isLetter(c) || Character.isDigit(c)) {
				outBuffer.append(Character.toUpperCase(c));
			}
		}

		return outBuffer.toString();
	}

	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o);
	}
}
