package team.boolbee.poc.concurrency.component;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CountDownLatchDemo {

	static Log logger = LogFactory.getLog(CountDownLatchDemo.class);
	static final int REFEREES = 3;
	static final int ATHLETES = 20;

	public static void main(String[] args) throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool();
		CountDownLatch startSignal = new CountDownLatch(REFEREES);
		CountDownLatch doneSignal = new CountDownLatch(ATHLETES);
		
		Referee.commandCounter = 0;
		for (int i = 0; i < REFEREES; i++) {
			exec.execute(new Referee(startSignal, doneSignal));
		} // for

		for (int i = 0; i < ATHLETES; i++) {
			exec.execute(new Athlete(startSignal, doneSignal));
		} // for

		exec.shutdown(); // Salir cuando todas las tareas se hayan completado
	}
}

class Athlete implements Runnable {
	private static int counter = 1;
	private final int id = counter++;
	private static Random ramdom = new Random();
	private final CountDownLatch startSignal;
	private final CountDownLatch doneSignal;

	public Athlete(CountDownLatch startSignal, CountDownLatch doneSignal) {
		this.startSignal = startSignal;
		this.doneSignal = doneSignal;
	}

	public void run() {
		try {
			CountDownLatchDemo.logger.info(this + "Ready!");
			startSignal.await(); // wait for all to finish
			doWork();
			doneSignal.countDown(); // let all threads proceed
		} catch (InterruptedException ex) {
			CountDownLatchDemo.logger.info(this + "interrupted");
		}
	}

	public void doWork() throws InterruptedException {
		CountDownLatchDemo.logger.info(this + "Starting up!");
		TimeUnit.MILLISECONDS.sleep(ramdom.nextInt(2000));
		CountDownLatchDemo.logger.info(this + "completed");
	}

	@Override
	public String toString() {
		return String.format("Athlete %1$-3d ", id);
	}
}

class Referee implements Runnable {
	private static int counter = 1;
	private final int id = counter++;
	private final CountDownLatch startSignal;
	private final CountDownLatch doneSignal;
	static final Object commandCounterlock = new Object();
	static Integer commandCounter = 0;

	public Referee(CountDownLatch startSignal, CountDownLatch doneSignal) {
		this.startSignal = startSignal;
		this.doneSignal = doneSignal;
	}

	public void run() {
		try {
			synchronized (commandCounterlock) {
				commandCounter++;
				TimeUnit.SECONDS.sleep(2);
				CountDownLatchDemo.logger.info(this + ((CountDownLatchDemo.REFEREES == commandCounter)
						? "3 2 1 GO!"
						: "Ready?"));
				startSignal.countDown(); // let all threads proceed
			}
			CountDownLatchDemo.logger.info(this + "Waiting for results");
			doneSignal.await();  // wait for all to finish
			CountDownLatchDemo.logger.info(this + "Publish Result");
		} catch (InterruptedException ex) {
			CountDownLatchDemo.logger.info(this + "interrupted");
		}
	}

	@Override
	public String toString() {
		return String.format("Referee %1$-3d ", id);
	}
}