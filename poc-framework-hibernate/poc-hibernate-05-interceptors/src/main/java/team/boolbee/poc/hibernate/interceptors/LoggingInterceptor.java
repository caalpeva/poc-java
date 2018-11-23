package team.boolbee.poc.hibernate.interceptors;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import team.boolbee.poc.hibernate.interceptors.model.Sale;

public class LoggingInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = -5315026433103286540L;

	private static Log logger = LogFactory.getLog("AUDIT");

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		if (entity instanceof Sale) {
			Sale sale = (Sale) entity;
			String trace = String.format("Sales has been inserted: %f\non: %s, \nby \"%s\"",
					sale.getTotal(), sale.getSalesDate(), sale.getSalesperson().getName());
			System.out.println(trace);
			logger.info(trace);
		}

		return super.onSave(entity, id, state, propertyNames, types);
	}

	@Override
	public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		if (entity instanceof Sale) {
			Sale sale = (Sale) entity;
			String trace = String.format("The following sale made on [%s] is about to be deleted, id=%s", sale.getSalesDate(), id);
			System.out.println(trace);
			logger.info(trace);
		}
	}

	@Override
	public String onPrepareStatement(String sql) {
		logger.info(sql);
		return super.onPrepareStatement(sql);
	}
}
