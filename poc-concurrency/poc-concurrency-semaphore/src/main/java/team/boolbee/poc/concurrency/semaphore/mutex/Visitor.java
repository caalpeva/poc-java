package team.boolbee.poc.concurrency.semaphore.mutex;

import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import team.boolbee.poc.concurrency.utils.ThreadUtils;

public class Visitor implements Runnable {

	private static Log logger = LogFactory.getLog(Visitor.class);
	
	private Museum museum;
	
	public Visitor(Museum museum) {
		this.museum = museum;
	}

	public void run() {
		try {
			museum.enter();
			seeMuseum();
			museum.exit();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void seeMuseum() throws InterruptedException {
		ThreadUtils.sleepQuietly(new Random().nextInt(2 * 1000) + 1000);
		logger.info("Thread " + Thread.currentThread().getName() + " See the museum..");
	}
}