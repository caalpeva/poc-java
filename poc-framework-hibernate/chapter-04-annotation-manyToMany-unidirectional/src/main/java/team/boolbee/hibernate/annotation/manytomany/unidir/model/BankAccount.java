package team.boolbee.hibernate.annotation.manytomany.unidir.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BankAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String number;
	private Float deposit;

	/**
	 * default constructor
	 */
	public BankAccount () {	
	}

	/**
	 * @param id
	 * @param number
	 * @param deposit
	 */
	public BankAccount(Long id, String number, Float deposit) {
		this.id = id;
		this.number = number;
		this.deposit = deposit;
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
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the deposit
	 */
	public Float getDeposit() {
		return deposit;
	}

	/**
	 * @param deposit the deposit to set
	 */
	public void setDeposit(Float deposit) {
		this.deposit = deposit;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BankAccount [id=" + id + ", number=" + number + ", deposit="
				+ deposit + "]";
	}
}
