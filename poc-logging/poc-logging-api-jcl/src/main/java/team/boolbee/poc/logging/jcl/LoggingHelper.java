package team.boolbee.poc.logging.jcl;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoggingHelper implements ILoggingHelper {

	private static Log logger = LogFactory.getLog(LoggingHelper.class);

	public void dumpChartSet() {
		logger.info(String.format("Default Charset: %s", Charset.defaultCharset()));
	}
	
	public void dumpCurrentDate() {
		// Do nothing
	}

	public void dumpDefaultLocale() {
		// Do nothing
	}
	
	public void dumpException(Throwable throwable) {
		//logger.info("Got an exception. " + throwable.getMessage());
		//logger.info("Just a stack trace, nothing to worry about", throwable);
		logger.error("BOOM!", throwable);
	}
	
	public void dumpVars(Map<String, ?> m) {
		List<String> keys = new ArrayList<String>(m.keySet());
		Collections.sort(keys);
		for (String k : keys) {
			logger.info(k + " : " + m.get(k));
		}
	}
}