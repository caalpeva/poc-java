package team.boolbee.poc.spring.model;

import java.util.Arrays;
import java.util.List;

public class Contact {

	private String name;
	private String email;
	private String[] filmTypes;
	private int rating;
	private List<String> notifications;
	private String comment;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String[] getFilmTypes() {		
		return filmTypes;
	}
	public void setFilmTypes(String[] filmTypes) {
		this.filmTypes = filmTypes;
	}	
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public List<String> getNotifications() {
		return notifications;
	}
	public void setNotifications(List<String> notifications) {
		this.notifications = notifications;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public String toString() {
		return "Contact [name=" + name + ", email=" + email + ", filmTypes=" + Arrays.toString(filmTypes) + ", rating="
				+ rating + ", notifications=" + notifications + ", comment=" + comment + "]";
	}
}