package team.boolbee.poc.springboot.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="JOBS")
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String description;
	
	@Column(name="publicationDate")
	private Date publicationDate;
	private Double salary;
	
	@OneToOne
	@JoinColumn(name = "categoryId")
	private Category category;
	
	@Type(type = "org.hibernate.type.NumericBooleanType")  
	private boolean featured = true;
	
	@Enumerated(EnumType.STRING)
	private Status status = Status.ACTIVE;
	private String details;
	private String image="no-image.png";
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getPublicationDate() {
		return publicationDate;
	}
	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public boolean isFeatured() {
		return featured;
	}
	public void setFeatured(boolean featured) {
		this.featured = featured;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public void resetImage() {
		this.image = null;
	}
	@Override
	public String toString() {
		return "Job [id=" + id + ", name=" + name + ", description=" + description + ", publicationDate="
				+ publicationDate + ", salary=" + salary + ", category=" + category + ", featured=" + featured
				+ ", status=" + status + ", details=" + details + ", image=" + image + "]";
	}	
}