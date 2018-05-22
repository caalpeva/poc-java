package team.boolbee.poc.logging;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LogRegister {

	private Log LOGGER = LogFactory.getLog(LogRegister.class);

	public void register(String message) {
		LOGGER.info(message);
	}

}