package team.boolbee.poc.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import team.boolbee.poc.spring.model.News;

@Repository
public interface NewsRepository extends CrudRepository<News, Integer> {

}