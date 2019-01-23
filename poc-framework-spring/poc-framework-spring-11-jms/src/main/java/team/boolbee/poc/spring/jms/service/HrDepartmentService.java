package team.boolbee.poc.spring.jms.service;

import team.boolbee.poc.spring.jms.model.Person;

public interface HrDepartmentService {
	public void processPersonInfo(Person person);
}