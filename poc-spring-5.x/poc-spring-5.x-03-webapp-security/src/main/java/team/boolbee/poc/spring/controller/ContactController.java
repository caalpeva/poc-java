package team.boolbee.poc.spring.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import team.boolbee.poc.spring.model.Contact;
import team.boolbee.poc.spring.service.MovieService;

@Controller
public class ContactController {

	@Autowired
	private MovieService movieService;

	@GetMapping("/contact")
	public String goContact(@ModelAttribute("contactInstance") Contact contact, Model model) {
		model.addAttribute("movieTypes", movieService.getMovieTypes());
		model.addAttribute("notificationList", getNotificationTypes());
		return "contactForm";
	}

	@PostMapping("/contact")
	public String save(@ModelAttribute("contactInstance") Contact contact,
			RedirectAttributes redirectAttributes) {
		System.out.println(contact);
		redirectAttributes.addFlashAttribute("successMessage", "Datos enviados correctamente");
		return "redirect:contact";
	}

	private List<String> getNotificationTypes() {
		List<String> notifications = new LinkedList<String>();
		notifications.add("Estrenos");
		notifications.add("Promociones");
		notifications.add("Noticias");
		notifications.add("Preventas");
		return notifications;
	}
}