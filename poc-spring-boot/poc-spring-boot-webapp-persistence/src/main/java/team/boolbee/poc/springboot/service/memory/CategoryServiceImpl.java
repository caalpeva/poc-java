package team.boolbee.poc.springboot.service.memory;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import team.boolbee.poc.springboot.model.Category;
import team.boolbee.poc.springboot.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	private List<Category> categories = new LinkedList<Category>();

	public CategoryServiceImpl() {
		Category category1 = new Category();
		category1.setId(1);
		category1.setName("Recursos Humanos");
		category1.setDescription("Trabajos relacionados con el area de RH.");
		
		Category category2 = new Category();
		category2.setId(2);
		category2.setName("Ventas");
		category2.setDescription("Ofertas de trabajo relacionados con ventas");
		
		Category category3 = new Category();
		category3.setId(3);
		category3.setName("Arquitectura");
		category3.setDescription("Diseño de planos en general y trabajos relacionados.");
		
		Category category4 = new Category();
		category4.setId(4);
		category4.setName("Hosteleria");
		category4.setDescription("Se necesita ingeniero para dar soporte a Intranet");
		
		Category category5 = new Category();
		category5.setId(5);
		category5.setName("Informatica");
		category5.setDescription("Diferentes sectores de informatica");
		
		categories.add(category1);
		categories.add(category2);
		categories.add(category3);
		categories.add(category4);
		categories.add(category5);
	}
	
	@Override
	public List<Category> list() {
		return categories;
	}

	@Override
	public Category findBy(Integer id) {
		for (Category category : categories) {
			if (category.getId().equals(id)) {
				return category;
			}
		}
		
		return null;
	}

	@Override
	public void add(Category category) {
		category.setId(getLastId() + 1);
		categories.add(category);		
	}
	
	private Integer getLastId() {
		return categories.size()> 0 ? categories.get(categories.size() - 1).getId(): 0;
	}
}
