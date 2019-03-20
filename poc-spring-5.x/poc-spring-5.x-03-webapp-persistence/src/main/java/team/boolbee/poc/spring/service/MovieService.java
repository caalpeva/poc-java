package team.boolbee.poc.spring.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import team.boolbee.poc.spring.model.FilmType;
import team.boolbee.poc.spring.model.Movie;

public interface MovieService {
	public List<Movie> findAll();
	public Page<Movie> findAll(Pageable pageable);
	public List<Movie> findAllActives();
	public List<Movie> findAllByShowtimeDate(Date date);
	public Movie findById(int movieId);
	public void save(Movie movie);
	public List<FilmType> getMovieTypes();
	public void delete(int movieId);
}