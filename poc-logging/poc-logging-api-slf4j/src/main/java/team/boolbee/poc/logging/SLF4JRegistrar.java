package team.boolbee.poc.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SLF4JRegistrar {

	private Logger LOGGER = LoggerFactory.getLogger(SLF4JRegistrar.class);

	public void register(String message) {
		LOGGER.info(message);
	}
}