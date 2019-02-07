package team.boolbee.poc.concurrency.semaphore.synchronization;

import java.util.concurrent.Semaphore;

public class ConditionalSynchronizationSolution {

	public static void main(String[] args) throws Exception {
		Semaphore semaphore = new Semaphore(0);
		new Thread(new ProcessOne(semaphore), "ProcessOne").start();
		new Thread(new ProcessTwo(semaphore), "ProcessTwo").start();
	}
}