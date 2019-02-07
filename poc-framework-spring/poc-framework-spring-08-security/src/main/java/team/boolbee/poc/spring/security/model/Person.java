package team.boolbee.poc.spring.security.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Person implements Serializable {
	
	private static final long serialVersionUID = 3972192228292927273L;
	
	private Integer id;
	private String name;
	private String surname;
	private Date birthDate;
	private String email;
	private List<Vehicle> vehicles = new ArrayList<Vehicle>();

	public Person() {}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	
	public void addVehicle(Vehicle vehicle) {
		vehicles.add(vehicle);
		vehicle.setPerson(this);
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
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", email='" + email + '\'' +
                '}';
	}
}