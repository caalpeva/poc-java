package team.boolbee.poc.concurrency.semaphore;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Consumer implements Runnable {

	private static Log logger = LogFactory.getLog(Consumer.class);
	
	private Buffer buffer;

	public Consumer(Buffer buffer) {
		this.buffer = buffer;
	}

	public void run() {
		try {
			while (true) {
				consume(buffer.remove());
				ThreadUtils.sleepQuietly(2000);
			} // while
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void consume(Object x) {
		logger.info("Thread " + Thread.currentThread().getName() + " consumed " + x);
	}
}