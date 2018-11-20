package team.boolbee.hibernate.xml.manytomany.unidir.model;

import java.util.HashSet;
import java.util.Set;

public class Customer {
	
	private Long id;
	private String name;
	private String address;
	private Set<BankAccount> bankAccounts = new HashSet<BankAccount>();
	
	/**
	 * default constructor
	 */
	public Customer () {
	}

	public Customer(String name, String address, Set<BankAccount> bankAccounts) {
		this(null, name, address, bankAccounts);
	}
	
	/**
	 * @param id
	 * @param name
	 * @param address
	 * @param bankAccounts
	 */
	public Customer(Long id, String name, String address,
			Set<BankAccount> bankAccounts) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.bankAccounts = bankAccounts;
	}
	
	/**
	 * adds new bank account to be owned by customer
	 * @param bankAccount
	 */
	public void addAccount(BankAccount bankAccount) {
		bankAccounts.add(bankAccount);
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
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the bankAccounts
	 */
	public Set<BankAccount> getBankAccounts() {
		return bankAccounts;
	}
	/**
	 * @param bankAccounts the bankAccounts to set
	 */
	public void setBankAccounts(Set<BankAccount> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Customer))
			return false;

		Customer that = (Customer) o;

		if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null)
			return false;
		if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null)
			return false;
//		if (getValue() != null ? !getValue().equals(that.getValue()) : that.getValue() != null)
//			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = getId() != null ? getId().hashCode() : 0;
		result = 31 * result + (getName() != null ? getName().hashCode() : 0);
		//result = 31 * result + (getValue() != null ? getValue().hashCode() : 0);
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", address=" + address
				+ ", bankAccounts=" + bankAccounts + "]";
	}
}
