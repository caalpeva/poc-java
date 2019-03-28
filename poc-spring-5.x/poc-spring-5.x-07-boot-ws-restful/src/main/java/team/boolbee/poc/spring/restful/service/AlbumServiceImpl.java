package team.boolbee.poc.spring.restful.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.boolbee.poc.spring.restful.model.Album;
import team.boolbee.poc.spring.restful.repository.AlbumRepository;

@Service
public class AlbumServiceImpl implements AlbumService {

	@Autowired
	private AlbumRepository albumRepository;
	
	@Override
	public List<Album> getAlbums() {
		return albumRepository.findAll();
	}

}