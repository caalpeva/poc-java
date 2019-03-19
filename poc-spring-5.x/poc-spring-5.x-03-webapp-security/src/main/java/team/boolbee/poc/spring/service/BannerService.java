package team.boolbee.poc.spring.service;

import java.util.List;

import team.boolbee.poc.spring.model.Banner;

public interface BannerService {
	List<Banner> findAll();
	List<Banner> findAllActives();
	Banner findById(int bannerId);
	void save(Banner banner);
	void delete(int bannerId);
}