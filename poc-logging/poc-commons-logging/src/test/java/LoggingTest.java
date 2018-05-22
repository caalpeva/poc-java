import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import junit.framework.TestCase;

public class LoggingTest extends TestCase {

	private Log LOGGER = LogFactory.getLog(LoggingTest.class);

	public void testLogging() {
		LOGGER.info("Esto es un mensaje");
	}

}