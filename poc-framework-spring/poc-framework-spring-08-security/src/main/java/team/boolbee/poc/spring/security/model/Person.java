package team.boolbee.poc.spring.security.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Person implements Serializable {
	
	private static final long serialVersionUID = 3972192228292927273L;
	
	private Integer id;
	private String name;
	private String surname;
	private Date birthDate;
	private String email;
	private String password;
	private List<Vehicle> vehicles = new ArrayList<Vehicle>();
	private Set<Privilege> privileges = new HashSet<Privilege>();

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
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