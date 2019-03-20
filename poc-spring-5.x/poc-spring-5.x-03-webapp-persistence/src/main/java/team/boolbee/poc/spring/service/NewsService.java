package team.boolbee.poc.spring.service;

import java.util.List;

import team.boolbee.poc.spring.model.News;

public interface NewsService {
	public List<News> findAll();
	public List<News> findLatest10();
	public News findById(int newsId);
	public void save(News news);
	public void delete(int newsId);
}