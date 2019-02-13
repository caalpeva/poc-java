package team.boolbee.poc.concurrency.basic.blocking;

import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InterruptingWithDelay {

	private static Log logger = LogFactory.getLog(InterruptingWithDelay.class);
	
	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			logger.info("usage: java InterruptingWithDelay delay-in-ms");
			System.exit(1);
		}
		
		Thread t = new Thread(new InterruptionChecker());
		t.start();
		TimeUnit.MILLISECONDS.sleep(new Integer(args[0]));
		logger.info("Issuing t.interrupt()");
		t.interrupt();
	}
}