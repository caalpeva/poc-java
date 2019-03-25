package team.boolbee.poc.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import team.boolbee.poc.spring.model.Banner;
import team.boolbee.poc.spring.service.BannerService;
import team.boolbee.poc.spring.utils.Utils;

@Controller
@RequestMapping("/banners")
public class BannerController {

	@Autowired
	private BannerService bannersService;
	
	@GetMapping("/index")
	public String goIndex(Model model) {
		model.addAttribute("banners", bannersService.findAll());
		return "banners/bannerList";
	}
	
	@GetMapping("/create")
	public String create(@ModelAttribute Banner banner) {
		return "banners/bannerForm";
	}
	
	@PostMapping("/save")
	public String save(Banner banner, @RequestParam("imageFile") MultipartFile multipartFile, HttpServletRequest request,
			BindingResult result, RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			System.err.println(result.getAllErrors());
			return "banners/bannerForm";
		}
		
		if (!multipartFile.isEmpty()) {
			String filename = Utils.saveImage(multipartFile, request);
			banner.setFilename(filename);
		}
		
		System.out.println(banner);
		bannersService.save(banner);
		redirectAttributes.addFlashAttribute("successMessage", "El registro fue guardado");
		return "redirect:/banners/index";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") int bannerId, Model model) {
		Banner banner = bannersService.findById(bannerId);
		model.addAttribute("banner", banner);
		return "banners/bannerForm";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int bannerId, RedirectAttributes attributes) {
		bannersService.delete(bannerId);
		attributes.addFlashAttribute("successMessage", "El registro fue eliminado");
		return "redirect:/banners/index";
	}
}