package team.boolbee.poc.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import team.boolbee.poc.spring.model.News;
import team.boolbee.poc.spring.model.Status;
import team.boolbee.poc.spring.repository.NewsRepository;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsRepository newsRepository;
	
	@Override
	public List<News> findAll() {
		return newsRepository.findAll(Sort.by("date").descending());
	}
	
	@Override
	public List<News> findLatest10() {
		return newsRepository.findTop10ByStatusOrderByDateDesc(Status.ACTIVE);
	}

	@Override
	public News findById(int newsId) {
		Optional<News> optional = newsRepository.findById(newsId);
		return (optional.isPresent())? optional.get(): null;
	}
	
	@Override
	public void save(News news) {
		newsRepository.save(news);
	}
	
	@Override
	public void delete(int newsId) {
		newsRepository.deleteById(newsId);
	}
}