package team.boolbee.poc.logging.jul;

import java.util.logging.Logger;

import junit.framework.TestCase;

public class Jdk14LoggingTest extends TestCase {

	// You must enable the java-logging mechanism by setting a special property
	// when starting the JVM :
	// java.exe -Djava.util.logging.config.file=logging.properties HttpConnect
	// and put in logging.properties file (by default in JRE_HOME\lib) the
	// following property
	// sun.net.www.protocol.http.HttpURLConnection.level = ALL

	@Override
	protected void setUp() throws Exception {
		try {
			String path = Jdk14LoggingTest.class.getClassLoader().getResource("logging.properties").getFile();
			System.setProperty("java.util.logging.config.file", path);
		} catch (SecurityException e) {
			// e.printStackTrace();
			Logger logger = Logger.getLogger("");
			logger.severe(e.getMessage());
		}
	}

	public void testURLConnection() {
		Downloader.downloadFromURL("");
		Downloader.downloadFromURL("http://zzz.zzzzz.zz");
		Downloader.downloadFromURL("http://www.google.es");
	}
}