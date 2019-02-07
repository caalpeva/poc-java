package team.boolbee.poc.concurrency.semaphore.synchronization;

import java.util.concurrent.Semaphore;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProcessOne implements Runnable {

	private static Log logger = LogFactory.getLog(ProcessOne.class);
	
	private Semaphore semaphore;
	
	public ProcessOne(Semaphore semaphore) {
		this.semaphore = semaphore;
	}

	public void run() {
		try {
			logger.info("A");
			semaphore.acquire();
			logger.info("B");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}