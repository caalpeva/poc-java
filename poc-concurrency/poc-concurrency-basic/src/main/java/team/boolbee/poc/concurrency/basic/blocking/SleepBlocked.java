package team.boolbee.poc.concurrency.basic.blocking;

import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SleepBlocked implements Runnable {

	private static Log logger = LogFactory.getLog(SleepBlocked.class);
	
	public void run() {
		try {
			while(true) {
				logger.info("Waiting for sleep()");
				TimeUnit.SECONDS.sleep(100);
			}
		} catch (InterruptedException e) {
			logger.info("InterruptedException");
		}
		
		logger.info("Exiting SleepBlocked.run()");
	}
}