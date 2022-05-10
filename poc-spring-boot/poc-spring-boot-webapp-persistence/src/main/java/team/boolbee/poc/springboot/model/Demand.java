package team.boolbee.poc.springboot.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DEMANDS")
public class Demand {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date date;
	private String comments;
	private String file; // El nombre del archivo PDF, DOCX del CV.

	@OneToOne
	@JoinColumn(name = "jobId")
	private Job job;

	@OneToOne
	@JoinColumn(name = "userId")
	private User user;

	public Demand() {

	}

	public Demand(Date fecha) {
		this.date = new Date();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Demand [id=" + id + ", date=" + date + ", comments=" + comments + ", file=" + file + ", job=" + job
				+ ", user=" + user + "]";
	}
}
