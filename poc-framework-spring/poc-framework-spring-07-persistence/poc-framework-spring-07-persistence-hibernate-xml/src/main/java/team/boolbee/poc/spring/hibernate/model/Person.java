package team.boolbee.poc.spring.hibernate.model;

import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable {
	
	private static final long serialVersionUID = 3972192228292927273L;
		
	private Integer id;
	private String firstName;
	private String lastName;
	private Date birthDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public boolean equals(Object o) {
		if (o instanceof Person) {
			Person person = (Person) o;
			return person.id.equals(this.id);
		} else {
			return false;
		}
	}

	@Override
    public String toString() {
        return "Person{" +
        		"id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                '}';
	}
}