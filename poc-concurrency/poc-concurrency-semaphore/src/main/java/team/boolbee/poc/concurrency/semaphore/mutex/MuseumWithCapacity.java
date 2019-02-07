package team.boolbee.poc.concurrency.semaphore.mutex;

import java.util.concurrent.Semaphore;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MuseumWithCapacity implements Museum {

	private static Log logger = LogFactory.getLog(MuseumWithCapacity.class);
	
	private Semaphore capacity;
	private Semaphore mutex;
	private int count = 0;
	
	public MuseumWithCapacity(int capacity) {
		this.capacity = new Semaphore(capacity);
		this.mutex = new Semaphore(1);
	}
	
	public void enter() throws InterruptedException {
		logger.info("Thread " + Thread.currentThread().getName() + " WAIT(capacity)");
		capacity.acquire();
		
		mutex.acquire();
		count++;
		logger.info("Thread " + Thread.currentThread().getName() + " -> Enter the museum");
		logger.info("Thread " + Thread.currentThread().getName() + " Active visitors: " + count);
		mutex.release();
	}
	
	public void exit() throws InterruptedException {
		mutex.acquire();
		count--;
		logger.info("Thread " + Thread.currentThread().getName() + " <- Leave the museum");
		logger.info("Thread " + Thread.currentThread().getName() + " Active visitors: " + count);
		mutex.release();
		
		logger.info("Thread " + Thread.currentThread().getName() + " SIGNAL(capacity)");
		capacity.release();
	}

}