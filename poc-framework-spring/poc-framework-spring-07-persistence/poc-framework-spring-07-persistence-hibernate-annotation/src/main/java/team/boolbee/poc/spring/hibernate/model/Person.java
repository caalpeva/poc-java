package team.boolbee.poc.spring.hibernate.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Person implements Serializable {
	
	private static final long serialVersionUID = 3972192228292927273L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String surname;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthDate;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "owner")
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

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	
	public void addVehicle(Vehicle vehicle) {
		vehicles.add(vehicle);
		vehicle.setOwner(this);
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
                '}';
	}
}