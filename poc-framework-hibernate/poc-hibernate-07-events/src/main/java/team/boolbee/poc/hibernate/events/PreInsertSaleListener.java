package team.boolbee.poc.hibernate.events;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;

import team.boolbee.poc.hibernate.events.model.Sale;

public class PreInsertSaleListener implements PreInsertEventListener {

	private static final long serialVersionUID = -5315026433103286540L;

	private static Log logger = LogFactory.getLog("AUDIT");

	public boolean onPreInsert(PreInsertEvent event) {
		Object entity = event.getEntity();
		if (entity instanceof Sale) {
			Sale sale = (Sale) entity;
			String trace = String.format("Sales has been inserted: %f\non: %s, \nby \"%s\"",
					sale.getTotal(), sale.getSalesDate(), sale.getSalesperson().getName());
			System.out.println(trace);
			logger.info(trace);
		}
		
		return false;
	}
}