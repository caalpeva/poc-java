package team.boolbee.poc.spring.service;

import java.util.Date;
import java.util.List;

import team.boolbee.poc.spring.model.Showtimes;

public interface ShowtimesService {
	
	public void save(Showtimes showtimes);
	public List<Showtimes> getShowTimes(int movieId, Date date);
	public Date findLatestShowtimesDate();
}