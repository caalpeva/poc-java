package team.boolbee.poc.spring.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import team.boolbee.poc.spring.model.Banner;
import team.boolbee.poc.spring.model.News;
import team.boolbee.poc.spring.service.BannerService;
import team.boolbee.poc.spring.service.MovieService;
import team.boolbee.poc.spring.service.NewsService;
import team.boolbee.poc.spring.service.ShowtimesService;
import team.boolbee.poc.spring.utils.Utils;

@Controller
public class HomeController {

	private final int NUMBER_OF_DAYS = 5;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	@Autowired
	private BannerService bannerService;
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private ShowtimesService showtimesService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String goHome(Model model) {
		Date startDate = getStartDate();
		//model.addAttribute("banners", bannerService.findAllActives());
		model.addAttribute("dates", Utils.getNextDays(startDate, NUMBER_OF_DAYS));
		model.addAttribute("searchDate", dateFormat.format(startDate));
		model.addAttribute("movies", movieService.findAllByShowtimeDate(startDate));
		//model.addAttribute("newsList", newsService.findLatest10());
		return "home";
	}
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public String searchMovies(@RequestParam("date") Date searchDate, Model model) {
		//model.addAttribute("banners", bannerService.findAllActives());
		model.addAttribute("dates", Utils.getNextDays(getStartDate(), NUMBER_OF_DAYS));
		model.addAttribute("searchDate", dateFormat.format(searchDate));
		model.addAttribute("movies", movieService.findAllByShowtimeDate(searchDate));
		//model.addAttribute("newsList", newsService.findLatest10());
		return "home";
	}
	
	@RequestMapping(value="detail/{id}/{searchDate}")
	public String goMovieDetail(@PathVariable("id") int movieId, @PathVariable("searchDate") Date date, Model model) {
		model.addAttribute("searchDate", dateFormat.format(date));
		model.addAttribute("showtimes", showtimesService.getShowTimes(movieId, date));
		model.addAttribute("movie", movieService.findById(movieId));
		return "movieDetail";
	}

	@ModelAttribute("banners")
	public List<Banner> getBanners() {
		return bannerService.findAllActives();
	}
	
	@ModelAttribute("newsList")
	public List<News> getNews() {
		return newsService.findLatest10();
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	private Date getStartDate() {
		// Se calcula una fecha de inicio en la que se conoce que existen datos
		Date startDate = showtimesService.findLatestShowtimesDate();
		if (startDate != null) {
//			Calendar calendar = Calendar.getInstance();
//			calendar.setTime(startDate);
//			calendar.add(Calendar.DATE, -NUMBER_OF_DAYS);
//			startDate = calendar.getTime();
		} else {
			startDate = new Date();			
		}
		return startDate;
	}
}