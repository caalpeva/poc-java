package team.boolbee.poc.spring.service;

import java.util.List;

import team.boolbee.poc.spring.model.Banner;

public interface BannerService {
	List<Banner> findAll();
	void save(Banner banner);
}