package team.boolbee.poc.spring.restful.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import team.boolbee.poc.spring.restful.model.Album;
import team.boolbee.poc.spring.restful.service.AlbumService;

@RestController
@RequestMapping("/api")
public class AlbumController {
	
	@Autowired
	private AlbumService albumService;
	
	@GetMapping("/albums")
	public List<Album> getAlbums() {
		return albumService.getAlbums();
	}

}