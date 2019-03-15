package team.boolbee.poc.spring.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import team.boolbee.poc.spring.model.News;
import team.boolbee.poc.spring.model.Status;

@Service
public class NewsServiceImpl implements NewsService {

	private List<News> newsList = new ArrayList<News>();	
	
	public NewsServiceImpl() {
		//SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		
		News news = new News();
		news.setId(getNextId());
		news.setTitle("Titulo");
		news.setDetail("Detalle");
		news.setDate(new Date());
		news.setStatus(Status.ACTIVE);
		newsList.add(news);
		
		news = new News();
		news.setId(getNextId());
		news.setTitle("Titulo2");
		news.setDetail("Detalle2");
		news.setDate(new Date());
		news.setStatus(Status.INACTIVE);
		newsList.add(news);
	}
	
	@Override
	public List<News> findAll() {
		return newsList;
	}

	@Override
	public void save(News news) {
		news.setId(getNextId());
		newsList.add(news);
	}
	
	private int getNextId() {
		int maxId = 0;
		for(News news: newsList) {
			maxId = Math.max(news.getId(), maxId);
		} // for

		return maxId + 1;
	}
}