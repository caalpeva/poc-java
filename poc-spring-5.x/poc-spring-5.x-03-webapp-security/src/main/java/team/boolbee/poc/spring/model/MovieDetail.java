package team.boolbee.poc.spring.model;

public class MovieDetail {
	public String director;
	public String actors;
	public String trailer;
	public String synopsis;
	
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
		return "MovieDetail [director=" + director + ", actors=" + actors + ", trailer=" + trailer + ", snopsis="
				+ synopsis + "]";
	}
}