package team.boolbee.poc.hibernate.events.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Sale implements Serializable {

	private static final long serialVersionUID = -2487287848448876327L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private double total;
	private float discount;
	private Date salesDate;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch= FetchType.EAGER)
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
