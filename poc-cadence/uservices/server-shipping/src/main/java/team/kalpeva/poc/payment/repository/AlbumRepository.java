package team.kalpeva.poc.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team.kalpeva.poc.payment.model.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {

}
