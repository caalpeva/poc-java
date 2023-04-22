package team.boolbee.poc.springboot.service.database;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import team.boolbee.poc.springboot.model.Category;
import team.boolbee.poc.springboot.repository.CategoryRepository;
import team.boolbee.poc.springboot.service.CategoryService;

@Service
@Primary
public class CategoryServiceInDatabaseImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<Category> list() {
		return categoryRepository.findAll();
	}

	@Override
	public Category findBy(Integer id) {
		Optional<Category> category = categoryRepository.findById(id);
		if (category.isPresent()) {
			return category.get();
		}
		
		return null;
	}

	@Override
	public void add(Category category) {
		categoryRepository.save(category);
	}

	@Override
	public void delete(Integer id) {
		categoryRepository.deleteById(id);
	}
	
	@Override
	public Page<Category> list(Pageable page) {
		return categoryRepository.findAll(page);
	}

}
