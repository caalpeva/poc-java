package team.boolbee.poc.hibernate.xml.inheritance.onetableperconcreteclass.model;

import org.testng.annotations.Test;

import team.boolbee.poc.hibernate.utils.GenericDAO;

public class OneTablePerConcreteClassXmlTest {
	
    @Test
    public void testOneTablePerConcreteClassXml() {
    	Drink drink = new Drink("Coke", 2.5f, true);
    	drink.setId(1L);
    	Food food = new Food("Salad", 6.0f, "Special Salad");
    	food.setId(2L);
    	Course course1 = new Course("Pasta", 8.5f, "Ravioli", "Starter");
    	course1.setId(3L);
    	Course course2 = new Course("Steak", 12f, "Raw meat", "Second course");
    	course2.setId(4L);
    	Dessert dessert = new Dessert("Cheesecake", 4.5f, "Special cheesecake", 500.f);
    	dessert.setId(5L);
    	
    	new GenericDAO<Drink>().insert(drink);
    	new GenericDAO<Food>().insert(food);
    	new GenericDAO<Dessert>().insert(dessert);
    	
    	GenericDAO<Course> courseDAO = new GenericDAO<Course>();
    	courseDAO.insert(course1);
    	courseDAO.insert(course2);
    	
		courseDAO.selectById(course1.getId(), Course.class);

		new GenericDAO<Product>().selectById(course1.getId(), Product.class);
    }
}