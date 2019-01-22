package team.boolbee.poc.jms.runnable;

import java.util.concurrent.CountDownLatch;

import javax.jms.JMSException;

import team.boolbee.poc.jms.Consumer;

public class ConsumedMessageCounter extends Worker {

	private Consumer consumer;
	private int count;
	
	public ConsumedMessageCounter(Consumer consumer, CountDownLatch startSignal, CountDownLatch doneSignal) {
		super(startSignal, doneSignal);
		this.consumer = consumer;
		count = 0;
	}
	
	@Override
	public void doWork() {
		try {
			while(true) {
				String message = consumer.receive(3000);
				if (message == null) {
					break;
				}
				
				count++;
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public int getCount() {
		return count;
	}
}