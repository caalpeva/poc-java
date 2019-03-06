package team.boolbee.poc.spring.model;

import java.util.Date;

public class News {
	private int id;
	private String title;
	private Date date = new Date();
	private String detail;
	private Status status = Status.ACTIVE;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", date=" + date + ", detail=" + detail + ", status=" + status
				+ "]";
	}
}