package team.boolbee.poc.hibernate.filters.xml.model;

import java.io.Serializable;
import java.util.Date;

public class Sale implements Serializable {

	private static final long serialVersionUID = -2487287848448876327L;

	private Long id;

	private double total;
	private float discount;
	private Date salesDate;

	private Salesperson salesperson;

	/**
	 * Constructor by default.
	 */
	public Sale() {

	}

	/**
	 * Constructor with parameters
	 * 
	 * @param id
	 * @param total
	 * @param discount
	 * @param salesDate
	 */
	public Sale(double total, float discount, Date salesDate) {
		this.total = total;
		this.discount = discount;
		this.salesDate = salesDate;
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
	 * @return the total
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(double total) {
		this.total = total;
	}

	/**
	 * @return the discount
	 */
	public float getDiscount() {
		return discount;
	}

	/**
	 * @param discount
	 *            the discount to set
	 */
	public void setDiscount(float discount) {
		this.discount = discount;
	}

	/**
	 * @return the salesDate
	 */
	public Date getSalesDate() {
		return salesDate;
	}

	/**
	 * @param salesDate
	 *            the salesDate to set
	 */
	public void setSalesDate(Date salesDate) {
		this.salesDate = salesDate;
	}
	
	

	/**
	 * @return the salesperson
	 */
	public Salesperson getSalesperson() {
		return salesperson;
	}

	/**
	 * @param salesperson the salesperson to set
	 */
	public void setSalesperson(Salesperson salesperson) {
		this.salesperson = salesperson;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Sale [id=" + id + ", total=" + total + ", discount=" + discount
				+ ", salesDate=" + salesDate + "]";
	}

	
}
