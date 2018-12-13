package team.boolbee.poc.hibernate.hql.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Supplier {

	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String name;

	// orphanRemoval = true
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "supplier", orphanRemoval = true) //, fetch = FetchType.EAGER) 
    List<Product> products = new ArrayList<Product>();
	
	/**
	 * Default constructor
	 */
	public Supplier() {

	}

	/**
	 * Constructor with parameters
	 * @param name
	 */
	public Supplier(String name) {
		this.name = name;
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
	 * @return the products
	 */
	public List<Product> getProducts() {
		return products;
	}

	/**
	 * @param products
	 *            the products to set
	 */
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	/**
	 * Añade un producto a la lista de productos
	 * 
	 * @param product
	 */
	public void addProduct(Product product) {
		this.products.add(product);
	}
	
	@Override
	public String toString() {
		return "Supplier [id=" + id
				+ ", name=" + name + "]";
	} 
}
