package team.boolbee.poc.hibernate.xml.inheritance.oneTable.model;

import org.testng.annotations.Test;

import team.boolbee.poc.hibernate.utils.GenericDAO;
import team.boolbee.poc.hibernate.xml.inheritance.onetable.model.Course;
import team.boolbee.poc.hibernate.xml.inheritance.onetable.model.Dessert;
import team.boolbee.poc.hibernate.xml.inheritance.onetable.model.Drink;
import team.boolbee.poc.hibernate.xml.inheritance.onetable.model.Food;

public class OneTablePerInheritanceHierarchyXmlTest {
	
    @Test
    public void testOneTablePerInheritanceHierarchyXml() {
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
    }
}