package team.boolbee.poc.spring.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="USERS")
public class User {
	@Id
	@Column(name = "username")
	private String name;
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Status status = Status.ACTIVE;
	private String email;
	private String phone;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "USERS_ROLES",
		joinColumns = @JoinColumn(name = "username"),
		inverseJoinColumns = @JoinColumn(name = "rolename"),
		uniqueConstraints = @UniqueConstraint(columnNames = { "username", "rolename" }))
	private List<Role> roles;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + ", status=" + status + ", email="
				+ email + ", phone=" + phone + ", roles=" + roles + "]";
	}
}