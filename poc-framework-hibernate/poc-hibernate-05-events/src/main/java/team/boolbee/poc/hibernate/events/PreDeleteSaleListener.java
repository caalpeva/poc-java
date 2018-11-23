package team.boolbee.poc.hibernate.events;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.event.spi.PreDeleteEvent;
import org.hibernate.event.spi.PreDeleteEventListener;

import team.boolbee.poc.hibernate.events.model.Sale;

public class PreDeleteSaleListener implements PreDeleteEventListener {

	private static final long serialVersionUID = -5315026433103286540L;

	private static Log logger = LogFactory.getLog("AUDIT");

	public boolean onPreDelete(PreDeleteEvent event) {
		Object entity = event.getEntity();
		if (entity instanceof Sale) {
			Sale sale = (Sale) entity;
			String trace = String.format("The following sale made on [%s] is about to be deleted, id=%s",
					sale.getSalesDate(), sale.getId());
			System.out.println(trace);
			logger.info(trace);
		}
		
		return false;
	}
}
