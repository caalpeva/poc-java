package team.boolbee.poc.concurrency.semaphore.synchronization;

import java.util.concurrent.Semaphore;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProcessTwo implements Runnable {

	private static Log logger = LogFactory.getLog(ProcessTwo.class);
	
	private Semaphore semaphore;
	
	public ProcessTwo(Semaphore semaphore) {
		this.semaphore = semaphore;
	}

	public void run() {
		logger.info("C");
		semaphore.release();
		logger.info("D");
	}
}