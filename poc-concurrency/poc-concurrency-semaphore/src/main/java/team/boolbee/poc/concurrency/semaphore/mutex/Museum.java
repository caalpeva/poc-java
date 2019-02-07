package team.boolbee.poc.concurrency.semaphore.mutex;

/**
 * An interface for Museum
 *
 */
public interface Museum {
	
	public abstract  void enter() throws InterruptedException;

	public abstract void exit() throws InterruptedException;
}