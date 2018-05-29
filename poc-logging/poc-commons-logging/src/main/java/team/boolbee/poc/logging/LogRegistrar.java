package team.boolbee.poc.logging;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LogRegistrar {

	private Log LOGGER = LogFactory.getLog(LogRegistrar.class);

	public void register(String message) {
		LOGGER.error(message);
		LOGGER.info("Mensaje en info");
	}

}