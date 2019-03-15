package team.boolbee.poc.spring.service;

import java.util.List;

import team.boolbee.poc.spring.model.FilmType;
import team.boolbee.poc.spring.model.Movie;

public interface MovieService {
	public List<Movie> findAll();
	public Movie findById(int movieId);
	public void save(Movie movie);
	public List<FilmType> getMovieTypes();
}