package team.boolbee.poc.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import team.boolbee.poc.spring.model.News;
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
		return newsRepository.findTop10ByOrderByDateDesc();
	}

	@Override
	public void save(News news) {
		newsRepository.save(news);
	}
}