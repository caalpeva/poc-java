package team.boolbee.poc.springboot.service;

import java.util.List;

import team.boolbee.poc.springboot.model.Category;

public interface CategoryService {
	List<Category> list();
	Category findBy(Integer id);
	void add(Category category);
}
