package team.boolbee.poc.hibernate.annotation.inheritance.onetablepersubclass.model;

import org.testng.annotations.Test;

import team.boolbee.poc.hibernate.annotation.inheritance.onetablepersubclass.model.Course;
import team.boolbee.poc.hibernate.annotation.inheritance.onetablepersubclass.model.Dessert;
import team.boolbee.poc.hibernate.annotation.inheritance.onetablepersubclass.model.Drink;
import team.boolbee.poc.hibernate.annotation.inheritance.onetablepersubclass.model.Food;
import team.boolbee.poc.hibernate.annotation.inheritance.onetablepersubclass.model.Product;
import team.boolbee.poc.hibernate.utils.GenericDAO;

public class OneTablePerConcreteClassAnnotationTest {
	
    @Test
    public void testOneTablePerConcreteClassAnnotation() {
    	Drink drink = new Drink("Coke", 2.5f, true);
    	Food food = new Food("Salad", 6.0f, "Special Salad");
    	Course course1 = new Course("Pasta", 8.5f, "Ravioli", "Starter");
    	Course course2 = new Course("Steak", 12f, "Raw meat", "Second course");
    	Dessert dessert = new Dessert("Cheesecake", 4.5f, "Special cheesecake", 500.f);
    	
    	new GenericDAO<Drink>().insert(drink);
    	new GenericDAO<Food>().insert(food);
    	new GenericDAO<Dessert>().insert(dessert);
    	
    	GenericDAO<Course> courseDAO = new GenericDAO<Course>();
    	courseDAO.insert(course1);
    	courseDAO.insert(course2);
    	
    	// Con la estrategia de mapeo de herencia de crear una tabla por cada subclase de la jerarquía,
		// se comprueba que al consultar por la entidad específica, el SQL generado por Hibernate
		// utiliza un join para unir cada una de las tres tablas que forman la jerarquia de dicha entidad.
		// En este caso son Course, Food y Product.
		courseDAO.selectById(course1.getId(), Course.class);

		// Sin embargo, si no se indica a que clase pertenece la instancia que queremos recuperar,
		// hibernate genera una consulta más compleja y cuantas mas entidades se tengan mas compleja
		// se volverá. Es por eso que esta estrategia es muy lenta si tenemos consultas polimórficas,
		// en las que no se conoce el tipo concreto de la clase que se busca, únicamente la clase base.
		new GenericDAO<Product>().selectById(course1.getId(), Product.class);
    }
}