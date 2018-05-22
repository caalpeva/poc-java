package team.boolbee.poc.beans;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Message {

	private Log LOGGER = LogFactory.getLog(Message.class);
	
	public Message() {
		LOGGER.info("Esto es un mensaje");
	}
	
}