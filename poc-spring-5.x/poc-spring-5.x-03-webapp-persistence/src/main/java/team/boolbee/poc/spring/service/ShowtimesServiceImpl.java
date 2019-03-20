package team.boolbee.poc.spring.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.boolbee.poc.spring.model.Showtimes;
import team.boolbee.poc.spring.repository.ShowtimesRepository;

@Service
public class ShowtimesServiceImpl implements ShowtimesService {
	
	@Autowired
	private ShowtimesRepository showtimesRepository;

	@Override
	public void save(Showtimes showtimes) {
		showtimesRepository.save(showtimes);
	}
	
	@Override
	public List<Showtimes> getShowTimes(int movieId, Date date) {
		return showtimesRepository.findByMovie_IdAndDateOrderByTime(movieId, date);
	}

	@Override
	public Date findLatestShowtimesDate() {
		return showtimesRepository.findLatestShowtimesDate();
	}
}