package team.boolbee.poc.concurrency.semaphore;

import java.util.concurrent.Semaphore;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BoundedBuffer implements Buffer {

	private static Log logger = LogFactory.getLog(BoundedBuffer.class);
	
	private static final int BUFFER_MAXSIZE = 3; // max size of buffer array
	private Object[] buffer; // array of Objects

	private int count; // number of items currently in the buffer
	private int nextFreePosition; // points to the next free position in the buffer
	private int firstFilledPosition; // points to the first filled position in the buffer
	private Semaphore mutex; // provides limited access to the buffer (mutual exclusion)
	private Semaphore space; // keep track of the number of empty elements in the array
	private Semaphore data; // keep track of the number of filled elements in the array

	public BoundedBuffer() {
		// buffer is initially empty
		count = 0;
		nextFreePosition = 0;
		firstFilledPosition = 0;
		buffer = new Object[BUFFER_MAXSIZE];
		mutex = new Semaphore(1); // 1 for mutual exclusion
		data = new Semaphore(0); // array begins with no elements
		space = new Semaphore(BUFFER_MAXSIZE); // array begins with all empty elements

	}

	public void insert(Object item) throws InterruptedException {
		// This provides synchronization for the producer,
		// because this makes the producer stop running when buffer is full
		logger.info("Thread " + Thread.currentThread().getName() + " WAIT(space)");
		space.acquire(); // keep track of number of empty elements (value--)
		
		mutex.acquire(); // mutual exclusion

		// add an item to the buffer
		++count;
		buffer[nextFreePosition] = item;
		nextFreePosition = (nextFreePosition + 1) % BUFFER_MAXSIZE;

		// buffer information feedback
		logger.info(String.format("-> Inserted \"%s\" count = %s, in = %s, out= %s %s",
				item,
				count,
				nextFreePosition,
				firstFilledPosition,
				(count == BUFFER_MAXSIZE)? "BUFFER FULL": ""));

		mutex.release(); // mutual exclusion
		
		logger.info("Thread " + Thread.currentThread().getName() + " SIGNAL(data)");
		data.release(); // keep track of number of elements (value++)
		// If buffer was empty, then this wakes up the Consumer
	}

	public Object remove() throws InterruptedException {
        // if the buffer is empty, then the buffer array cannot be used
		logger.info("Thread " + Thread.currentThread().getName() + " WAIT(data)");
		data.acquire(); // keep track of number of elements (value--)
           
		// This provides synchronization for consumer, 
		// because this makes the Consumer stop running when buffer is empty

		mutex.acquire(); //mutual exclusion
		
		// remove an item from the buffer
		--count;
		Object item = buffer[firstFilledPosition];
		firstFilledPosition = (firstFilledPosition + 1) % BUFFER_MAXSIZE;
      
        //buffer information feedback
		logger.info(String.format("<- Removed \"%s\" count = %s, in = %s, out= %s %s",
				item,
				count,
				nextFreePosition,
				firstFilledPosition,
				(count == 0)? "BUFFER EMPTY": ""));
		
        mutex.release(); //mutual exclusion
        
        logger.info("Thread " + Thread.currentThread().getName() + " SIGNAL(space)");
        space.release(); // keep track of number of empty elements (value++)
      	//if buffer was full, then this wakes up the Producer
        
        return item;
	}
}