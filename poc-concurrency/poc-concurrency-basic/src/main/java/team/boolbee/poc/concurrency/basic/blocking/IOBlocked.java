package team.boolbee.poc.concurrency.basic.blocking;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class IOBlocked implements Runnable {

	private static Log logger = LogFactory.getLog(IOBlocked.class);
	
	private InputStream in;
	
	public IOBlocked(InputStream is) {
		in = is;
	}
	
	public void run() {
		try {
			logger.info("Waiting for read()");
			in.read();
		} catch (IOException e) {
			if (Thread.currentThread().isInterrupted()) {
				logger.info("Interrupted from blocked I/O");				
			} else {
				throw new RuntimeException(e);
			}
		}
		
		logger.info("Exiting IOBlocked.run()");
	}
}