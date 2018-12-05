package team.boolbee.poc.hibernate.criteria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Product {

    public enum Status { ACTIVE, INACTIVE }
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    @Column(nullable = false)
    private String name;
    
    private String description;
	
    @Column(nullable = false)
    private Double price;
    
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;
    
    @ManyToOne
    private Supplier supplier;
	
	/**
	 * default constructor
	 */
	public Product () {
		
	}
	
	/**
	 * Constructor with parameters
	 * @param name
	 * @param description
 	 * @param price
	 * @param supplier
	 */
	public Product(String name, String description, Double price, Supplier supplier) {
		this.name = name;
        this.description = description;
        this.price = price;
        this.supplier = supplier;
    }
	
	/**
	 * Constructor with parameters
	 * @param name
	 * @param description
 	 * @param price
 	 * @param status
	 * @param supplier
	 */
	public Product(String name, String description, Double price, Status status, Supplier supplier) {
		this(name, description, price, supplier);
        this.status = status;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the supplier
	 */
	public Supplier getSupplier() {
		return supplier;
	}

	/**
	 * @param supplier the supplier to set
	 */
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Product [id=" + id
				+ ", name=" + name
				+ ",description=" + description
				+ ", price=" + price
				+ ", status=" + status + "]";
	} 
	
	
}
