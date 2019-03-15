package team.boolbee.poc.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import team.boolbee.poc.spring.model.Banner;
import team.boolbee.poc.spring.model.Status;

@Service
public class BannerServiceImpl implements BannerService {

	private List<Banner> banners = new ArrayList<Banner>();
	
	public BannerServiceImpl() {
		Banner banner = new Banner();
		banner.setId(getNextId());
		banner.setTitle("King Kong");
		banner.setFilename("slide1.jpg");
		banner.setStatus(Status.ACTIVE);
		banners.add(banner);
		
		banner = new Banner();
		banner.setId(getNextId());
		banner.setTitle("Cars 3");
		banner.setFilename("slide3.jpg");
		banner.setStatus(Status.INACTIVE);
		banners.add(banner);
		
		banner = new Banner();
		banner.setId(getNextId());
		banner.setTitle("Spiderman");
		banner.setFilename("slide4.jpg");
		banner.setStatus(Status.INACTIVE);
		banners.add(banner);
	}

	public List<Banner> findAll() {
		return banners;
	}
	
	public void save(Banner banner) {
		banner.setId(getNextId());
		banners.add(banner);
	}
	
	private int getNextId() {
		int maxId = 0;
		for(Banner banner: banners) {
			maxId = Math.max(banner.getId(), maxId);
		} // for

		return maxId + 1;
	}
}