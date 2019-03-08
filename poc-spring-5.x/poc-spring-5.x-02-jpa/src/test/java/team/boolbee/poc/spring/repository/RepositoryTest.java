package team.boolbee.poc.spring.repository;

import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import team.boolbee.poc.spring.model.News;
import team.boolbee.poc.spring.model.Status;

public class RepositoryTest {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-service.xml");
		News news = new News();
		news.setTitle("Noticia de ultima hora");
		news.setDate(new Date());
		news.setDetail("Detalles de la noticia");
		news.setStatus(Status.ACTIVE);
		
		System.out.println(news.toString());
		
		NewsRepository newsRepository = (NewsRepository) applicationContext.getBean("newsRepository", NewsRepository.class);
		newsRepository.save(news);
		
		System.out.println(news.toString());
		
		applicationContext.close();
	}
}
