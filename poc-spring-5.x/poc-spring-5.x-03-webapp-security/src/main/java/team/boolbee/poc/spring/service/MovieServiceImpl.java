package team.boolbee.poc.spring.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.boolbee.poc.spring.model.FilmType;
import team.boolbee.poc.spring.model.Movie;
import team.boolbee.poc.spring.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	@Override
	public List<Movie> findAll() {
		return movieRepository.findAll();
	}

	@Override
	public Movie findById(int movieId) {
		return findById(movieId);
	}

	@Override
	public void save(Movie movie) {
		movieRepository.save(movie);
	}

	@Override
	public List<FilmType> getMovieTypes() {
		return Arrays.asList(FilmType.values());
	}
}