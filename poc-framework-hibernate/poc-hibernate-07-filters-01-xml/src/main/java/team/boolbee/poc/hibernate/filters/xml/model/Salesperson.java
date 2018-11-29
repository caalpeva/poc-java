package team.boolbee.poc.hibernate.filters.xml.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Salesperson implements Serializable {

	private static final long serialVersionUID = -3499088984211468204L;

	private Long id;

	private String name;
	private float salary;
	private float commission;
	private boolean active;
	private Set<Sale> sales = new HashSet<Sale>();
	private Set<String> areas;

	/**
	 * Constructor by default.
	 */
	public Salesperson() {

	}

	/**
	 * Constructor with parameters.
	 * 
	 * @param name
	 * @param salary
	 * @param commision
	 * @param sales
	 */
	public Salesperson(String name, float salary, float commision, boolean active) {
		this.name = name;
		this.salary = salary;
		this.commission = commision;
		this.active = active;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the salary
	 */
	public float getSalary() {
		return salary;
	}

	/**
	 * @param salary
	 *            the salary to set
	 */
	public void setSalary(float salary) {
		this.salary = salary;
	}

	/**
	 * @return the commision
	 */
	public float getCommission() {
		return commission;
	}

	/**
	 * @param commision
	 *            the commision to set
	 */
	public void setCommission(float commission) {
		this.commission = commission;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return the sales
	 */
	public Set<Sale> getSales() {
		return sales;
	}

	/**
	 * @param sales
	 *            the sales to set
	 */
	public void setSales(Set<Sale> sales) {
		this.sales = sales;
	}

	/**
	 * Allow to add a specific sale to the set.
	 * 
	 * @param sale
	 */
	public void addSales(Sale sale) {
		this.sales.add(sale);
		sale.setSalesperson(this);
	}

	public Set<String> getAreas() {
		return areas;
	}

	public void setAreas(Set<String> areas) {
		this.areas = areas;
	}

	public void addAreas(String... areasSet) {
		if (getAreas() == null) {
			setAreas(new HashSet<String>());
		}
		
		getAreas().addAll(Arrays.asList(areasSet));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Salesperson [id=" + id + ", name=" + name + ", salary=" + salary + ", commision=" + commission
				+ ", active=" + active + ", areas=" + areas + ", sales=" + sales + "]";
	}

}
