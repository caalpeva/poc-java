package team.boolbee.poc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team.boolbee.poc.spring.model.News;

@Repository
//public interface NewsRepository extends CrudRepository<News, Integer> {
//public interface NewsRepository extends PagingAndSortingRepository<News, Integer> {
public interface NewsRepository extends JpaRepository<News, Integer> {

}