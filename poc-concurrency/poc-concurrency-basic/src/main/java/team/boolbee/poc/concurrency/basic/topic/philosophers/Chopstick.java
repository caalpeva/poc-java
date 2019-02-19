package team.boolbee.poc.concurrency.basic.topic.philosophers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Chopstick {

	private static Log logger = LogFactory.getLog(Chopstick.class);
	
	private boolean taken = false;
	
	public synchronized void take() throws InterruptedException {
		while(taken) {
			logger.info(Thread.currentThread().getName() + " Wait for chopstick");
			wait();
		}
		
		taken = true;
		logger.info(Thread.currentThread().getName() + " Chopstick Taken");
	}
	
	public synchronized void drop() {
		taken = false;
		notifyAll();
		logger.info(Thread.currentThread().getName() + " notifyAll chopstick");
	}
}