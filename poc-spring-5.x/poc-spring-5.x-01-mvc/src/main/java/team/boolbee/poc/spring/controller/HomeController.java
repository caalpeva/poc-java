package team.boolbee.poc.spring.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import team.boolbee.poc.spring.service.BannerService;
import team.boolbee.poc.spring.service.MovieService;
import team.boolbee.poc.spring.service.NewsService;
import team.boolbee.poc.spring.utils.Utils;

@Controller
public class HomeController {

	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	@Autowired
	private BannerService bannerService;
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private NewsService newsService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String goHome(Model model) {
		model.addAttribute("dates", Utils.getNextDays(5));
		model.addAttribute("searchDate", dateFormat.format(new Date()));
		model.addAttribute("banners", bannerService.findAll());
		model.addAttribute("movies", movieService.findAll());
		model.addAttribute("newsList", newsService.findAll());
		return "home";
	}
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public String searchMovies(@RequestParam("date") String searchDate, Model model) {
		model.addAttribute("dates", Utils.getNextDays(5));
		model.addAttribute("searchDate", searchDate);
		model.addAttribute("movies", movieService.findAll());
		model.addAttribute("newsList", newsService.findAll());
		return "home";
	}
	
	@RequestMapping(value="detail/{id}/{searchDate}")
	public String goMovieDetail(@PathVariable("id") int movieId, @PathVariable("searchDate") String searchDate, Model model) {
		model.addAttribute("movie", movieService.findById(movieId));
		return "movieDetail";
	}
}