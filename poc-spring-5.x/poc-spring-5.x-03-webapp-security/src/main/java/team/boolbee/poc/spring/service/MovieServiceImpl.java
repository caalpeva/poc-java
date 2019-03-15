package team.boolbee.poc.spring.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import team.boolbee.poc.spring.model.FilmType;
import team.boolbee.poc.spring.model.Movie;
import team.boolbee.poc.spring.model.Status;

@Service
public class MovieServiceImpl implements MovieService {

	List<Movie> movies = new ArrayList<Movie>();
	
	public MovieServiceImpl() {
		System.out.println("Constructor MovieService");
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Movie movie = new Movie();
			movie.setId(getNextId());
			movie.setTitle("Ghostbusters");
			movie.setDuration(105);
			movie.setClassification("B");
			movie.setFilename("ghostbusters.png");
			movie.setType(FilmType.COMEDY);
			movie.setReleaseDate(formatter.parse("08-06-1984"));
			movie.setStatus(Status.INACTIVE);
			movie.getDetail().setActors("Bill Murray, Dan Aykroyd, Sigourney Weaver, Harold Ramis, Ernie Hudson");
			movie.getDetail().setDirector("Ivan Reitman");
			movie.getDetail().setTrailer("https://www.youtube.com/embed/nr3rshIFA_0");
			movie.getDetail().setSynopsis("A los doctores Venkman, Stantz y Spengler, expertos en parapsicología, no les conceden una beca de investigación que habían solicitado. Al encontrarse sin trabajo, deciden fundar la empresa \"Los Cazafantasmas\", dedicada a limpiar Nueva York de ectoplasmas. El aumento repentino de apariciones espectrales en la ciudad será el presagio de la llegada de un peligroso y poderoso demonio.");
			movies.add(movie);
			
			movie = new Movie();
			movie.setId(getNextId());
			movie.setTitle("The Goonies");
			movie.setDuration(114);
			movie.setClassification("C");
			movie.setFilename("goonies.jpg");
			movie.setType(FilmType.ADVENTURE);
			movie.setReleaseDate(formatter.parse("07-06-1985"));
			movie.setStatus(Status.INACTIVE);
			movies.add(movie);
			
			movie = new Movie();
			movie.setId(getNextId());
			movie.setTitle("Labyrinth");
			movie.setDuration(101);
			movie.setClassification("B");
			movie.setFilename("labyrinth.jpg");
			movie.setType(FilmType.FANTASY);
			movie.setReleaseDate(formatter.parse("19-12-1986"));
			movie.setStatus(Status.INACTIVE);
			movies.add(movie);
			
			movie = new Movie();
			movie.setId(getNextId());
			movie.setTitle("Ghost");
			movie.setDuration(128);
			movie.setClassification("A");
			movie.setFilename("ghost.jpg");
			movie.setType(FilmType.DRAMA);
			movie.setReleaseDate(formatter.parse("13-07-1990"));
			movie.setStatus(Status.INACTIVE);
			movies.add(movie);
			
			movie = new Movie();
			movie.setId(getNextId());
			movie.setTitle("Titanic");
			movie.setDuration(195);
			movie.setClassification("A");
			movie.setFilename("titanic.jpg");
			movie.setType(FilmType.DRAMA);
			movie.setReleaseDate(formatter.parse("19-12-1997"));
			movie.setStatus(Status.ACTIVE);
			movies.add(movie);

			movie = new Movie();
			movie.setId(getNextId());
			movie.setTitle("Godzilla");
			movie.setDuration(139);
			movie.setClassification("A");
			movie.setFilename("godzilla.jpg");
			movie.setType(FilmType.SCIENCE_FICTION);
			movie.setReleaseDate(formatter.parse("20-05-1998"));
			movie.setStatus(Status.INACTIVE);
			movies.add(movie);
			
			movie = new Movie();
			movie.setId(getNextId());
			movie.setTitle("The Green Mile");
			movie.setDuration(189);
			movie.setClassification("A");
			movie.setFilename("the-green-mile.jpg");
			movie.setType(FilmType.SCIENCE_FICTION);
			movie.setReleaseDate(formatter.parse("10-12-1999"));
			movie.setStatus(Status.INACTIVE);
			movies.add(movie);
			
			movie = new Movie();
			movie.setId(getNextId());
			movie.setTitle("Gladiator");
			movie.setDuration(155);
			movie.setClassification("C");
			movie.setFilename("gladiator.png");
			movie.setType(FilmType.ACTION);
			movie.setReleaseDate(formatter.parse("05-05-2000"));
			movie.setStatus(Status.INACTIVE);
			movies.add(movie);
		} catch(ParseException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	@Override
	public List<Movie> findAll() {
		return movies;
	}

	@Override
	public Movie findById(int movieId) {
		for(Movie movie: movies) {
			if (movie.getId() == movieId) {
				return movie;
			}
		} // for
		
		return null;
	}

	@Override
	public void save(Movie movie) {
		movie.setId(getNextId());
		movies.add(movie);
	}
	
	private int getNextId() {
		int maxId = 0;
		for(Movie movie: movies) {
			maxId = Math.max(movie.getId(), maxId);
		} // for

		return maxId + 1;
	}

	@Override
	public List<FilmType> getMovieTypes() {
		return Arrays.asList(FilmType.values());
	}
}