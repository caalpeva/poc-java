package team.boolbee.hibernate.annotation.manytomany.unidir.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String address;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch= FetchType.EAGER)
	private Set<BankAccount> bankAccounts = new HashSet<BankAccount>();
	
	/**
	 * default constructor
	 */
	public Customer () {
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", address=" + address
				+ ", bankAccounts=" + bankAccounts + "]";
	}
}
