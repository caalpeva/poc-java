package team.boolbee.poc.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SLF4JRegister {

	private Logger LOGGER = LoggerFactory.getLogger(SLF4JRegister.class);

	public void register(String message) {
		LOGGER.info(message);
	}
}