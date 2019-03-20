package team.boolbee.poc.spring.model;

import java.util.Date;

public class Showtimes {
	
	private int id;
	private Date date;
	private String time; // HH:mm
	private String room; 
	private double price;
	private Movie movie;

	public Showtimes() { }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	@Override
	public String toString() {
		return "ShowTimes [id=" + id + ", date=" + date + ", time=" + time + ", room=" + room + ", price=" + price
				+ ", movie=" + movie + "]";
	}
}