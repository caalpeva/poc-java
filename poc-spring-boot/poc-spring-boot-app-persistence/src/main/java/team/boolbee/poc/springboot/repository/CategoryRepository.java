package team.boolbee.poc.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import team.boolbee.poc.springboot.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
