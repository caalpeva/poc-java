package team.boolbee.poc.concurrency.semaphore;


public class ProducerAndConsumerSolution {

	private static final int PRODUCERS = 4;
	private static final int CONSUMERS = 2;
	
	public static void main(String[] args) throws Exception {
		// Instantiate (create) buffer shared by Producers & Consumers
		Buffer sharedBuffer; sharedBuffer = new BoundedBuffer();
		//BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(3);
		
		// Create the producer and consumer threads
		for(int index = 1; index <= PRODUCERS; index++) {
			new Thread(new Producer(sharedBuffer), "Producer-" + index).start();
		} // for
		
		for(int index = 1; index <= CONSUMERS; index++) {
			new Thread(new Consumer(sharedBuffer), "Consumer-" + index).start();
		} // for
	}
}