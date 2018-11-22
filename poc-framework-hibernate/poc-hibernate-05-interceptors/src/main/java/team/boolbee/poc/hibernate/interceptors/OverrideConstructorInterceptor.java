package team.boolbee.poc.hibernate.interceptors;

import java.io.Serializable;

import org.hibernate.EmptyInterceptor;
import org.hibernate.EntityMode;
import org.hibernate.type.Type;

import team.boolbee.poc.hibernate.interceptors.model.MissingDefaultConstructorObject;
import team.boolbee.poc.hibernate.interceptors.model.Sale;

public class OverrideConstructorInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = 5940476829416689908L;

	@Override
	public Object instantiate(String entityName, EntityMode entityMode, Serializable id) {
		if (entityName.equals(MissingDefaultConstructorObject.class.getName())) {
			return new MissingDefaultConstructorObject(null, null);
		} else {
			// Some other class - continue to default handling
			return super.instantiate(entityName, entityMode, id);
		}
	}

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		if (entity instanceof Sale) {
			System.out.println("Sales has");
		}

		return super.onSave(entity, id, state, propertyNames, types);
	}

	public Object readResolve() {
		System.out.println("Executing readResolve");
		return OverrideConstructorInterceptor.getInstance(); // FIXME
	}

	private static OverrideConstructorInterceptor obj = null;

	public OverrideConstructorInterceptor() {
	      System.out.println("Executing constructor");
   }

	public static OverrideConstructorInterceptor getInstance() {
		if (obj == null) {
			obj = new OverrideConstructorInterceptor();
		}
		System.out.println("An instance is returned");
		return obj;
	}
	
	 @Override
	   public String toString() {
	      return "OverrideContructorInterceptor [i=1]";
	   }
}