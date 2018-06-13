package team.boolbee.poc.logging.slf4j;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import junit.framework.Assert;
import junit.framework.TestCase;

public class HttpConnectionTest extends TestCase {

	static {
		SLF4JBridgeHandler.removeHandlersForRootLogger(); // (since SLF4J 1.6.5)

		// add SLF4JBridgeHandler to j.u.l's root logger, should be done once
		// during
		// the initialization phase of your application
		SLF4JBridgeHandler.install();
		java.util.logging.Logger.getLogger("").setLevel(Level.FINEST);
		// https://www.slf4j.org/api/org/slf4j/bridge/SLF4JBridgeHandler.html
		// https://stackoverflow.com/questions/9117030/jul-to-slf4j-bridge
	}

	public void testHttpConnection() {
		java.util.logging.Logger logger = java.util.logging.Logger.getLogger("HOLA");
		//Logger slf4jLogger = LoggerFactory.getLogger(HttpConnectionTest.class);
		URL url;
		try {
			logger.log(Level.INFO, "PRUEBA MEDIANTE JUL");
			//slf4jLogger.info("Log by slf4j");
			url = new URL(
					"http://demoadmin.mhealthalert.com:8080/desktop-hub-nutriox/mhealthalert-desktop-hub-app-2.7.1-distribution.zip");
			// url = new
			// URL("http://demoserver.mhealthalert.com/desktop-hub-nutriox/mhealthalert-desktop-hub-app-2.7.1-distribution.zip");
			Assert.assertNotSame(-1, getFileSize(url));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private int getFileSize(URL url) {
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) url.openConnection();
			// conn.setRequestMethod("HEAD");
			// conn.getInputStream();
			return conn.getContentLength();
		} catch (IOException e) {
			return -1;
		} finally {
			conn.disconnect();
		}
	}

}