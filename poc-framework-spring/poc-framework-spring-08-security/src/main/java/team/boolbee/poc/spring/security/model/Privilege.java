package team.boolbee.poc.spring.security.model;

import java.io.Serializable;

public class Privilege implements Serializable {
	
	private static final long serialVersionUID = 3972192228277927243L;

	private Integer id;
	
	private String permission;

	public Privilege() {}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}	
	
	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	@Override
    public String toString() {
        return "Person{" +
        		"id=" + id +
                ", permission='" + permission + '\'' +
                '}';
	}
}