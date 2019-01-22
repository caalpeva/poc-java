package team.boolbee.poc.jms;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;

import junit.framework.TestCase;
import team.boolbee.poc.jms.runnable.ConsumedMessageCounter;

public class JmsTopicTest extends TestCase {

	private Connection connection;
	private Session session;
	private Topic topic;

	@Override
	protected void setUp() throws Exception {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		connection = connectionFactory.createConnection();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		topic = new ActiveMQTopic("testTopic");
	}

	public void testWhenSubscribersAreCreatedBeforeSending() throws JMSException, InterruptedException {
		final int numConsumers = 2;
		CountDownLatch startSignal = new CountDownLatch(1);
		CountDownLatch doneSignal = new CountDownLatch(numConsumers);
		List<ConsumedMessageCounter> consumedMessageCounters = new ArrayList<ConsumedMessageCounter>(numConsumers);

		try {
			// Inicializar la recepción y envío de los mensajes
			connection.start();

			for (int i = 1; i <= numConsumers; i++) { // create consumers and start threads
				ConsumedMessageCounter counter = new ConsumedMessageCounter(
						new Consumer("Subscriber " + i, session, topic), startSignal, doneSignal);
				consumedMessageCounters.add(counter);
				new Thread(counter).start();
			} // for

			Producer producer = new Producer("Publisher", session, topic);
			startSignal.countDown(); // let all threads proceed

			final int numMessages = 5;
			for (int i = 1; i <= numMessages; i++) {
				producer.send(String.format("Hello World! (%d)", i));
				Thread.sleep(500);
			}

			doneSignal.await(); // wait for all to finish

			int total = 0;
			for(ConsumedMessageCounter counter: consumedMessageCounters) {
				assertEquals(numMessages, counter.getCount());
				total += counter.getCount();
			} // for
			
			assertEquals(numMessages * numConsumers, total);
			
			// Parar el envío y recepción de mensajes
			connection.stop();

		} finally {
			session.close();
			connection.close();
		}
	}

	public void testWhenSubscribersAreCreatedAfterSending() throws JMSException, InterruptedException {
		final int numConsumers = 2;
		CountDownLatch startSignal = new CountDownLatch(1);
		CountDownLatch doneSignal = new CountDownLatch(numConsumers);
		List<ConsumedMessageCounter> consumedMessageCounters = new ArrayList<ConsumedMessageCounter>(numConsumers);

		try {
			// Inicializar la recepción y envío de los mensajes
			connection.start();

			final int numMessages = 5;
			Producer producer = new Producer("Publisher", session, topic);
			for (int i = 1; i <= numMessages; i++) {
				producer.send(String.format("Hello World! (%d)", i));
				Thread.sleep(500);
			}

			startSignal.countDown(); // let all threads proceed
			for (int i = 1; i <= numConsumers; i++) { // create consumers and start threads
				ConsumedMessageCounter counter = new ConsumedMessageCounter(
						new Consumer("Subscriber " + i, session, topic), startSignal, doneSignal);
				consumedMessageCounters.add(counter);
				new Thread(counter).start();
			} // for

			doneSignal.await(); // wait for all to finish

			for(ConsumedMessageCounter counter: consumedMessageCounters) {
				assertEquals(0, counter.getCount());
			} // for
			
			// Parar el envío y recepción de mensajes
			connection.stop();

		} finally {
			session.close();
			connection.close();
		}
	}
}