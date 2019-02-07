package team.boolbee.poc.concurrency.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Utilities for causing a thread to sleep. Note, we should be handling
 * interrupted exceptions but choose not to do so for code clarity.
 *
 */
public class ThreadUtils {

	private static final int NAP_TIME = 5000; // max nap time in seconds

	private static Log logger = LogFactory.getLog(ThreadUtils.class);
	
	/**
	 * Nap between zero and NAP_TIME seconds.
	 */
	public static void sleepQuietly() {
		sleepQuietly(NAP_TIME);
	}

	/**
	 * Nap between zero and duration seconds.
	 */
	public static void sleepQuietly(long duration) {
		logger.info("Thread " + Thread.currentThread().getName() + " nap for " + duration + " millisseconds");
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			// method sleep() throws InterruptedException - if any thread has
			// interrupted the current thread.
			//System.out.println("ERROR in nap(): " + e);
		}
	}
}