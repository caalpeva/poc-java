package team.boolbee.poc.hibernate.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
    private String name;
	private Calendar birthDate;
	@Transient
	private int age;

    public Person() {
    }

    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getBirthdate() {
		return birthDate;
	}

	public void setBirthDate(Calendar birthDate) {
		this.birthDate = birthDate;
	}
	
	public int getAge() {
		if (birthDate == null) {
			return 0;
		}
		
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) - birthDate.get(Calendar.MONTH);
		int day = now.get(Calendar.DAY_OF_MONTH) - birthDate.get(Calendar.DAY_OF_MONTH);

		// Se ajusta el año dependiendo el mes y el día
		// month < 0 todavía no ha llegado el mes de su cumpleaños
		// (month == 0 && day < 0) estamos en el mes de su cumpleaños pero no hemos llegado todavía al día en concreto
		if (month < 0 || (month == 0 && day < 0)) {
			year--;
		}

		return year;
	}

	@Override
    public String toString() {
        return "Person{" +
        		"id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}