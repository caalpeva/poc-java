package team.boolbee.poc.concurrency.semaphore.topic.producerandconsumer;

/**
 * An interface for buffers
 *
 */
public interface Buffer {
	/**
	 * insert an item into the Buffer. Note this may be either a blocking or
	 * non-blocking operation.
	 * @throws InterruptedException 
	 */
	public abstract void insert(Object item) throws InterruptedException;

	/**
	 * remove an item from the Buffer. Note this may be either a blocking or
	 * non-blocking operation.
	 * @throws InterruptedException 
	 */
	public abstract Object remove() throws InterruptedException;
}