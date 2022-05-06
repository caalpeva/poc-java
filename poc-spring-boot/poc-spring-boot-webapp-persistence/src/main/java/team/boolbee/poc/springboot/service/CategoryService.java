package team.boolbee.poc.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import team.boolbee.poc.springboot.model.Category;

public interface CategoryService {
	List<Category> list();
	Page<Category> list(Pageable page);
	Category findBy(Integer id);
	void add(Category category);
	void delete(Integer id);
}
