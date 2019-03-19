package team.boolbee.poc.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import team.boolbee.poc.spring.model.Movie;
import team.boolbee.poc.spring.model.Status;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
	public List<Movie> findByStatusOrderByTitle(Status status);
	
	@Query("select s.movie.id from Showtimes s where s.movie.status = 'ACTIVE' and s.date = :date group by s.movie.id order by s.movie.id asc")
	public List<Integer> findMovieIdsByShowtimeDate(@Param("date") Date date);
}