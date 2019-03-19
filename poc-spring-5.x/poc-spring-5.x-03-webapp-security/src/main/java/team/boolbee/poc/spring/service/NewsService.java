package team.boolbee.poc.spring.service;

import java.util.List;

import team.boolbee.poc.spring.model.News;

public interface NewsService {
	public List<News> findAll();
	public List<News> findLatest10();
	public void save(News news);
}