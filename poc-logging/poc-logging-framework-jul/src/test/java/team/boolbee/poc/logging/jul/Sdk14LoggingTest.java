package team.boolbee.poc.logging.jul;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import junit.framework.TestCase;

public class Sdk14LoggingTest extends TestCase {

	public Logger logger = Logger.getLogger("");
	
	static {
		Handler fh;
		try {
			String path = Island.class.getClassLoader().getResource("jul-logging.properties").getFile();
			System.setProperty("java.util.logging.config.file", path);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	You must enable the java-logging mechanism by setting a special property when starting the JVM :
//
//		java.exe -Djava.util.logging.config.file=logging.properties HttpConnect
//		and put in logging.properties file (by default in JRE_HOME\lib) the following property
//		sun.net.www.protocol.http.HttpURLConnection.level = ALL
	
	public void testHttpConnection() {
		logger.setLevel(Level.FINE);
		URL url;
		HttpURLConnection con = null;
		try {
			logger.finest("Connecting... (FINEST)");
			logger.finer("Connecting... (FINER)");
			logger.fine("Connecting... (FINE)");
			logger.info("Connecting... (INFO)");
			
			url = new URL("http://www.google.es");
			con = (HttpURLConnection) url.openConnection();
			Reader reader;
			reader = new InputStreamReader(con.getInputStream());
			while (true) {
				int ch = reader.read();
				if (ch == -1) {
					break;
				}
				System.out.print((char) ch);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			logger.warning("Connecting... (SERVERE)");
		} catch (IOException e) {
			logger.severe("Connecting... (SERVERE)");
			e.printStackTrace();
		} finally {
			con.disconnect();
		}

	}
}