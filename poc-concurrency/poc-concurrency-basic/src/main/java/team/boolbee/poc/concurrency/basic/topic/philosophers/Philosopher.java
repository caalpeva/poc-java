package team.boolbee.poc.concurrency.basic.topic.philosophers;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Philosopher implements Runnable {

	private static Log logger = LogFactory.getLog(Philosopher.class);
	
	@SuppressWarnings("unused")
	private Random random = new Random();
	private Chopstick left;
	private Chopstick right;
	@SuppressWarnings("unused")
	private int ponderFactor;
	
	public Philosopher(Chopstick left, Chopstick right, int ponder) {
		this.left = left;
		this.right = right;
		ponderFactor = ponder;
	}
	
	public void run() {
		try {
			while(!Thread.interrupted()) {
				logger.info(this + " thinking");
				pause();
				logger.info(this + " grabbing a stick");
				right.take();
				pause();
				logger.info(this + " grabbing other stick");
				left.take();
				logger.info(this + " eatting");
				pause();
				right.drop();
				left.drop();
			} // while
		} catch(InterruptedException e) {
			logger.info(this + " exiting via interrupt");
		}
	}
	
	private void pause() throws InterruptedException {
		//TimeUnit.MILLISECONDS.sleep(random.nextInt(ponderFactor * 250));
		TimeUnit.MILLISECONDS.sleep(2000);
	}
	
	@Override
	public String toString() {
		return Thread.currentThread().getName();
	}
}