package team.boolbee.poc.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Details")
public class MovieDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	public String director;
	public String actors;
	public String trailer;
	
	@Column(name="synopsis", columnDefinition="TEXT")
	public String synopsis;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getActors() {
		return actors;
	}
	public void setActors(String actors) {
		this.actors = actors;
	}
	public String getTrailer() {
		return trailer;
	}
	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	
	@Override
	public String toString() {
		return "MovieDetail [id=" + id + ", director=" + director + ", actors=" + actors + ", trailer=" + trailer
				+ ", synopsis=" + synopsis + "]";
	}
}