package team.boolbee.poc.concurrency.semaphore.topic.producerandconsumer;

import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import team.boolbee.poc.concurrency.utils.ThreadUtils;

public class Producer implements Runnable {

	private static Log logger = LogFactory.getLog(Producer.class);
	
	private Buffer buffer;
	private Random random = new Random();

	public Producer(Buffer buffer) {
		this.buffer = buffer;
	}

	public void run() {
		try {
			while (true) {
				buffer.insert(produce());
				ThreadUtils.sleepQuietly(6000);
			} // while
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private int produce() {
		int retVal = random.nextInt(100);
		logger.info("Thread " + Thread.currentThread().getName() + " produced " + retVal);
		return retVal;
	}
}