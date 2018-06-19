package team.boolbee.poc.logging.jcl;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StartupManager {

	private static Log logger = LogFactory.getLog(StartupManager.class);

	private ILoggingHelper loggingHelper;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public final void init() {
		logger.info("===== ENVIRONMENT VARIABLES =====");
		loggingHelper.dumpVars(System.getenv());

		logger.info("======= SYSTEM PROPERTIES =======");
		loggingHelper.dumpVars(new HashMap(System.getProperties()));
		
		loggingHelper.dumpChartSet();
		loggingHelper.dumpDefaultLocale();
		loggingHelper.dumpCurrentDate();

		try {
			manageData();
		} catch (Throwable t) {
			loggingHelper.dumpException(t);
		}
	}

	public void manageData() {
		generateException();
	}
	
	private void generateException() {
		String a = null;
		a.equals(null);		
	}
	
	public ILoggingHelper getLoggingHelper() {
		return loggingHelper;
	}

	public void setLoggingHelper(ILoggingHelper loggingHelper) {
		this.loggingHelper = loggingHelper;
	}
}