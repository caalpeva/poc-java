package team.boolbee.poc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team.boolbee.poc.spring.model.Banner;
import team.boolbee.poc.spring.model.Status;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Integer> {
	public List<Banner> findByStatusOrderByDateDesc(Status status);
}