package team.boolbee.poc.spring.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import team.boolbee.poc.spring.model.News;
import team.boolbee.poc.spring.model.Status;

@SpringJUnitConfig(TestContext.class)
@ContextConfiguration("/spring-datasource-hibernate.xml")
@Transactional
public class NewsRepositoryTest {

	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	// @Autowired
	// private ApplicationContext applicationContext;

	@Autowired
	private NewsRepository newsRepository;

	@Test
	public void saveAndUpdateNews() {
		// assertNotNull(applicationContext);
		// NewsRepository newsRepository = (NewsRepository) applicationContext.getBean("newsRepository", NewsRepository.class);
		
		long count = newsRepository.count();

		News news = new News();
		news.setTitle("Las primeras reacciones a 'Dumbo' son muy positivas");
		news.setDate(new Date());
		news.setDetail("La saga de películas de acción real de Disney estrenará su nueva aventura el próximo 29 de marzo.");
		news.setStatus(Status.ACTIVE);
		System.out.println(news.toString());

		newsRepository.save(news);

		assertNotEquals(0, news.getId());
		System.out.println(news.toString());

		assertTrue(newsRepository.existsById(news.getId()));

		assertEquals(count + 1, newsRepository.count());
	
		Optional<News> optional = newsRepository.findById(news.getId());
		if (!optional.isPresent()) {
			fail("Debe exitir un elemento");
		}

		Date date = null;
		try {
			date = format.parse("2019-03-12");
		} catch (ParseException e) {
			fail(e);
		}

		news = optional.get();
		news.setDate(date);
		news.setStatus(Status.INACTIVE);
		newsRepository.save(news);

		optional = newsRepository.findById(news.getId());
		if (!optional.isPresent()) {
			fail("Debe exitir un elemento");
		}

		assertEquals(date, optional.get().getDate());
	}

	@Test
	public void checkNews() {
		populateData();
		List<News> newsList = (List<News>) newsRepository.findAll();
		assertEquals(newsRepository.count(), newsList.size());

		for (News news: newsList) {
			System.out.println(news);
		}
		
		List<Integer> ids = new LinkedList<Integer>();
		ids.add(1);
		ids.add(2);
		ids.add(4);
		newsList = (List<News>) newsRepository.findAllById(ids);
		assertEquals(ids.size(), newsList.size());
	}

	@Test()
	//@Transactional
	public void deleteNews() {
		News news = new News();
		news.setTitle("Las primeras reacciones a 'Dumbo' son muy positivas");
		news.setDate(new Date());
		news.setDetail("La saga de películas de acción real de Disney estrenará su nueva aventura el próximo 29 de marzo.");
		news.setStatus(Status.ACTIVE);
		System.out.println(news.toString());

		newsRepository.save(news);
		
		long count = newsRepository.count();
		
		Optional<News> optional = newsRepository.findById(news.getId());
		if (!optional.isPresent()) {
			fail("Debe exitir un elemento");
		}
		
		newsRepository.delete(optional.get());
		//newsRepository.deleteById(optional.get().getId());
		assertEquals(--count, newsRepository.count());
		
		//newsRepository.deleteAll();
		newsRepository.deleteAllInBatch(); // Método de borrado más eficiente
		assertEquals(0, newsRepository.count());
	}
	
	@Test
	public void sort() {
		List<News> newsList = newsRepository.findAll(Sort.by("title").descending());
		String lastTitle = null;
		for (News news: newsList) {
			System.out.println(news);
			if (lastTitle != null && (lastTitle.compareTo(news.getTitle()) < 0)) {
				fail("No descending");
			}
			lastTitle = news.getTitle();
		}
	}
	
	@Test
	public void page() {
		long count = newsRepository.count();
		System.out.println("Elementos: " + count);
		
		int total = 0;
		final int pageSize = 7;
		int pageNums = (int) ((count / pageSize) + ((count % pageSize) > 0? 1: 0));
		for(int index = 0; index < pageNums; index++) {
			//Page<News> newsPages = newsRepository.findAll(PageRequest.of(index, pageSize));
			Page<News> newsPages = newsRepository.findAll(PageRequest.of(index, pageSize, Sort.by("title")));
			for (News news: newsPages) {
				System.out.println(news);
			}
			
			total += newsPages.getNumberOfElements();
		} // for
		
		assertEquals(count, total);
	}
	
	private void populateData() {
		createNews("'Aladdin': Will Smith homenajea al Genio de Robin Williams",
				"Disney parece estar decidido a reinventar todos y cada uno de los clásicos animados "
				+ "que dieron al estudio la popularidad que tiene hoy en día.",
				new Date());
		createNews("La película de 'Dragones y Mazmorras' está en busca de su protagonista",
				"La nueva película de Dragones y mazmorras es uno de los proyectos más interesantes de los próximos años.",
				new Date());
		createNews("Oscar Isaac confirma que 'Star Wars: Episodio 9' será el final a las 9 películas de los Skywalker",
				"Star Wars: Episodio IX es una de las películas más esperadas de 2019.",
				new Date());
	}

	private void createNews(String title, String detail, Date date) {
		News news = new News();
		news.setTitle(title);
		news.setDate(date);
		news.setDetail(detail);

		newsRepository.save(news);
	}
}