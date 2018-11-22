package team.boolbee.poc.hibernate.interceptors;

import java.io.Serializable;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import team.boolbee.poc.hibernate.interceptors.model.Sale;

public class AuditSalesInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = -5315026433103286540L;
	
	//private static Log logger = LogFactory.getLog(AuditSalesInterceptor.class);

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		if (entity instanceof Sale) {
			Sale sale = (Sale) entity;
			System.out.println(String.format("Sales has been inserted: %f\non: %s, \nby \"%s\"", sale.getTotal(),
					sale.getSalesDate(), sale.getSalesperson().getName()));
		}

		return super.onSave(entity, id, state, propertyNames, types);
	}

	@Override
	public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		if (entity instanceof Sale) {
			Sale sale = (Sale) entity;
			System.out.println(String.format("The following sale made on [%s] is about to be deleted, id=%s",
					sale.getSalesDate(), id));
		}
	}
	
	@Override
	public String onPrepareStatement(String sql) {
//		logger.info("Logging SQL statement ...... start");
//		logger.info(sql);
//		logger.info("Logging SQL statement ...... end");
		return super.onPrepareStatement(sql);
	}
}
