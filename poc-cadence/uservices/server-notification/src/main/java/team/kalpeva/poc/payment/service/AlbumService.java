package team.kalpeva.poc.payment.service;

import java.util.List;

import team.kalpeva.poc.payment.model.Album;

public interface AlbumService {
	public List<Album> getAlbums();
	public void save(Album album);
	public void deleteById(int albumId);
}