package team.boolbee.poc.spring.jms.service;

import team.boolbee.poc.spring.jms.model.Person;

public class HrDepartmentServiceImpl implements HrDepartmentService {

	public void processPersonInfo(Person person) {
		System.out.println("Processing message...");
		System.out.println(person);
	}
}