package team.boolbee.poc.spring.restful.service;

import java.util.List;

import team.boolbee.poc.spring.restful.model.Album;

public interface AlbumService {
	public List<Album> getAlbums();
	public void save(Album album);
	public void deleteById(int albumId);
}