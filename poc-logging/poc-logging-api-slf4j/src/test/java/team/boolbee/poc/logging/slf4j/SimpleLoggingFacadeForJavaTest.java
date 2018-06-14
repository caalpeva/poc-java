package team.boolbee.poc.logging.slf4j;

import java.util.logging.Logger;

import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import team.boolbee.poc.logging.jcl.StartupManager;
import team.boolbee.poc.logging.jul.Sdk14LoggingTest;

public class SimpleLoggingFacadeForJavaTest extends AbstractDependencyInjectionSpringContextTests {

	@Override
	protected String[] getConfigLocations() {
		return new String[] { "applicationContext.xml" };
	}

	@Override
	protected void onSetUp() throws Exception {
		try {
//			You must enable the java-logging mechanism by setting a special property when starting the JVM :
//					java.exe -Djava.util.logging.config.file=logging.properties HttpConnect
//					and put in logging.properties file (by default in JRE_HOME\lib) the following property
//					sun.net.www.protocol.http.HttpURLConnection.level = ALL
			String path = SimpleLoggingFacadeForJavaTest.class.getClassLoader().getResource("jul-logging.properties").getFile();
			System.setProperty("java.util.logging.config.file", path);
		} catch (SecurityException e) {
			//e.printStackTrace();
			Logger logger = Logger.getLogger("");
			logger.severe(e.getMessage());
		}
		
		SLF4JBridgeHandler.removeHandlersForRootLogger(); // (since SLF4J 1.6.5)
		// add SLF4JBridgeHandler to j.u.l's root logger, should be done once
		// during the initialization phase of your application
		SLF4JBridgeHandler.install();
	}

	public void testLogging() {
		StartupManager startupManager = (StartupManager) getApplicationContext().getBean("startupManager");
		startupManager.init();
	}
}