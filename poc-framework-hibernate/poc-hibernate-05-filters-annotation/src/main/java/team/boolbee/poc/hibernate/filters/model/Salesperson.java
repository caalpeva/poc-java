package team.boolbee.poc.hibernate.filters.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

@Entity
@FilterDefs({
	@FilterDef(name = "byStatus", parameters = @ParamDef(name = "status", type = "boolean")),
	@FilterDef(name = "byArea", parameters = @ParamDef(name = "area", type = "string")),
	@FilterDef(name = "nameEndsWithAlbertson"),
	@FilterDef(name = "bySalesDate", parameters = @ParamDef(name = "salesDate", type = "date")) })
// @FilterDef(name = "bySalesDate", parameters = @ParamDef(name = "salesDate", type = "date"))

@Filters({
	@Filter(name = "byStatus", condition = "active = :status"),
	@Filter(name = "byArea", condition = ":area in (select ua.areas from salesperson_areas ua where ua.salesperson_id = id)"),
	// El filtro declarado en la entidad sobre una propiedad de colección aplica unicamente a los resultados de dicha entidad
	@Filter(name = "nameEndsWithAlbertson", condition = "name like '%Albertson'") })
public class Salesperson implements Serializable {

	private static final long serialVersionUID = -3499088984211468204L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private float salary;
	private float commission;
	private boolean active;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, mappedBy = "salesperson")
	@Filter(name = "bySalesDate", condition = "salesDate > :salesDate")
	// El filtro declarado en la propiedad de colección aplica únicamente los datos de dicha colección
	private Set<Sale> sales = new HashSet<Sale>();

	@ElementCollection(fetch = FetchType.EAGER)
	//@CollectionTable(name = "SALESPERSON_AREAS")
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
