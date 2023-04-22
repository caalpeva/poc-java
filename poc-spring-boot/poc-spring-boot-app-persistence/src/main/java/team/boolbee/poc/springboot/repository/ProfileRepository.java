package team.boolbee.poc.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import team.boolbee.poc.springboot.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {

}
