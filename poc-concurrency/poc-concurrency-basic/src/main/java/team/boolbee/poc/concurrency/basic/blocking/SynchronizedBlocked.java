package team.boolbee.poc.concurrency.basic.blocking;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SynchronizedBlocked implements Runnable {

	private static Log logger = LogFactory.getLog(SynchronizedBlocked.class);
	
	public SynchronizedBlocked() {
		new Thread () {
			@Override
			public void run() {
				f(); // Bloqueo adquirido por esta hebra
			}
		}.start();
	}
	
	public synchronized void f() {
		while(true) { // Nunca libera el bloqueo
			Thread.yield();
		}
	}
	
	public void run() {
		logger.info("Trying to call f()");
		f();
		logger.info("Exiting SynchronizedBlocked.run()");
	}
}