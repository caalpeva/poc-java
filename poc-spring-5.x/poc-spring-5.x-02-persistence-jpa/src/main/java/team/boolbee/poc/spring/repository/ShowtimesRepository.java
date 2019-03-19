package team.boolbee.poc.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import team.boolbee.poc.spring.model.Showtimes;

@Repository
public interface ShowtimesRepository extends JpaRepository<Showtimes, Integer> {

	public List<Showtimes> findByMovie_IdAndDateOrderByTime(int movieId, Date date);
	
	@Query("select max(s.date) from Showtimes s where s.movie.status = 'ACTIVE'")
	public Date findLatestShowtimesDate();
}