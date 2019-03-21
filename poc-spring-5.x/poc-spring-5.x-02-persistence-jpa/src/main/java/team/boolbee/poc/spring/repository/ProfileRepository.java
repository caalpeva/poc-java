package team.boolbee.poc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team.boolbee.poc.spring.model.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {

}
