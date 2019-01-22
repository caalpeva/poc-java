package team.boolbee.poc.jms.runnable;

import java.util.concurrent.CountDownLatch;

public abstract class Worker implements Runnable {
	private final CountDownLatch startSignal;
	private final CountDownLatch doneSignal;

	Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
		this.startSignal = startSignal;
		this.doneSignal = doneSignal;
	}

	public void run() {
		try {
			startSignal.await();
			doWork();
			doneSignal.countDown();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	public abstract void doWork();
}