package team.boolbee.poc.concurrency.basic.cooperation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

class Car {
	private boolean isWaxed = false;

	public synchronized void waxed() {
		isWaxed = true;
		notifyAll();
	}

	public synchronized void buffed() {
		isWaxed = false;
		notifyAll();
	}

	public synchronized void waitForWaxing() throws InterruptedException {
		while (!isWaxed) {
			wait();
		}
	}

	public synchronized void waitForBuffing() throws InterruptedException {
		while (isWaxed) {
			wait();
		}
	}
}

class WaxOn implements Runnable {
	private Car car;

	public WaxOn(Car car) {
		this.car = car;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				car.waitForBuffing();
				WaxMachine.logger.info("Wax On!");
				TimeUnit.MILLISECONDS.sleep(500);
				car.waxed();
			} // while
		} catch (InterruptedException e) {
			WaxMachine.logger.info("Exiting via interrupt");
		}

		WaxMachine.logger.info("Ending Wax On task");
	}
}

class BuffOn implements Runnable {
	private Car car;

	public BuffOn(Car car) {
		this.car = car;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				car.waitForWaxing();
				WaxMachine.logger.info("Buff On!");
				TimeUnit.MILLISECONDS.sleep(500);
				car.buffed();
			} // while
		} catch (InterruptedException e) {
			WaxMachine.logger.info("Exiting via interrupt");
		}

		WaxMachine.logger.info("Ending Buff On task");
	}
}

public class WaxMachine {

	static Log logger = LogFactory.getLog(WaxMachine.class);
	
	public static void main(String[] args) throws InterruptedException {
		Car car = new Car();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new WaxOn(car));
		exec.execute(new BuffOn(car));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow(); // Interrumpir todas las tareas
	}
}