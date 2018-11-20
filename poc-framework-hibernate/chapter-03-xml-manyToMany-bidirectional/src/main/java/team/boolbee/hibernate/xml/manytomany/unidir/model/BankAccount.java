package team.boolbee.hibernate.xml.manytomany.unidir.model;

import java.util.HashSet;
import java.util.Set;

public class BankAccount {

	private Long id;
	private String number;
	private Float deposit;
	private Set<Customer> customers = new HashSet<Customer>();

	/**
	 * default constructor
	 */
	public BankAccount() {
	}

	public BankAccount(String number, Float deposit) {
		this(null, number, deposit);
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
	 * @param id
	 *            the id to set
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
	 * @param number
	 *            the number to set
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
	 * @param deposit
	 *            the deposit to set
	 */
	public void setDeposit(Float deposit) {
		this.deposit = deposit;
	}

	public Set<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}

	public void addCustomer(Customer customer) {
		customers.add(customer);
		customer.addAccount(this);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof BankAccount))
			return false;

		BankAccount that = (BankAccount) o;

		if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null)
			return false;
		if (getNumber() != null ? !getNumber().equals(that.getNumber()) : that.getNumber() != null)
			return false;
//		if (getValue() != null ? !getValue().equals(that.getValue()) : that.getValue() != null)
//			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = getId() != null ? getId().hashCode() : 0;
		result = 31 * result + (getNumber() != null ? getNumber().hashCode() : 0);
		//result = 31 * result + (getValue() != null ? getValue().hashCode() : 0);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BankAccount [id=" + id + ", number=" + number + ", deposit=" + deposit + "]";
	}
}
