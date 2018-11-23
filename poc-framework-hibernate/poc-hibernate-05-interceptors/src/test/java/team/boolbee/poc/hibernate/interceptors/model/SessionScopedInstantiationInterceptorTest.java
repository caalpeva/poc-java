package team.boolbee.poc.hibernate.interceptors.model;

import org.testng.annotations.Test;

import team.boolbee.poc.hibernate.interceptors.InstantiationInterceptor;
import team.boolbee.poc.hibernate.utils.GenericDAO;
import team.boolbee.poc.hibernate.utils.GenericInterceptorDAO;

public class SessionScopedInstantiationInterceptorTest {

	private Long objectId = null;

	@Test
	public void saveMissingDefaultCtorObject() {
		MissingDefaultCtorObject object = new MissingDefaultCtorObject("No default constructor");
		GenericDAO<MissingDefaultCtorObject> genericDAO = new GenericDAO<MissingDefaultCtorObject>();
		genericDAO.insert(object);
		objectId = object.getId();
	}

	@Test(dependsOnMethods = "saveMissingDefaultCtorObject", expectedExceptions = org.hibernate.InstantiationException.class)
	public void testNoReadObjectFailure() {
		GenericDAO<MissingDefaultCtorObject> genericDAO = new GenericDAO<MissingDefaultCtorObject>();
		System.out.println(genericDAO.selectById(objectId, MissingDefaultCtorObject.class));
	}

	@Test(dependsOnMethods = "saveMissingDefaultCtorObject")
	public void readMissingDefaultCtorObject() {
		GenericInterceptorDAO<MissingDefaultCtorObject> genericDAO = new GenericInterceptorDAO<MissingDefaultCtorObject>(
				new InstantiationInterceptor());
		System.out.println(genericDAO.selectById(objectId, MissingDefaultCtorObject.class));
	}
}