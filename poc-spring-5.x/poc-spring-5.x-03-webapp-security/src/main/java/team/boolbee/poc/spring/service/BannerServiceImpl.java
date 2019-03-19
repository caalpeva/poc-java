package team.boolbee.poc.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.boolbee.poc.spring.model.Banner;
import team.boolbee.poc.spring.model.Status;
import team.boolbee.poc.spring.repository.BannerRepository;

@Service
public class BannerServiceImpl implements BannerService {

	@Autowired
	private BannerRepository bannerRepository;

	public List<Banner> findAll() {
		return bannerRepository.findAll();
	}
	
	@Override
	public List<Banner> findAllActives() {
		return bannerRepository.findByStatusOrderByDateDesc(Status.ACTIVE);
	}
	
	public void save(Banner banner) {
		bannerRepository.save(banner);
	}
}