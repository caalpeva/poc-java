package team.boolbee.poc.spring.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team.boolbee.poc.spring.restful.model.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {

}
