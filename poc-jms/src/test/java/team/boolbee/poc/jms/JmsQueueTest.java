package team.boolbee.poc.jms;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import junit.framework.TestCase;
import team.boolbee.poc.jms.runnable.ConsumedMessageCounter;

/**
 * Clase encargada de comprobar el funcionamiento del modelo de mensajeria punto a punto de JMS.
 * Cuando se proporciona un mensaje al corredor de mensajes (message broker), éste lo coloca en una cola (destination).
 * La cola es responsable de mantener el mensaje hasta que un receptor esté preparado para recuperarlo.
 * Como el mensaje se elimina de la cola cuando se entrega, se garantiza que el mensaje se entregará sólo a un receptor.
 */
public class JmsQueueTest extends TestCase {
	private Connection connection;
	private Session session;
	private Queue queue;

	@Override
	protected void setUp() throws Exception {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		connection = connectionFactory.createConnection();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		queue = new ActiveMQQueue("testQueue");
	}

	public void testWhenConsumersAreCreatedBeforeSending() throws JMSException, InterruptedException {
		final int numConsumers = 2;
		CountDownLatch startSignal = new CountDownLatch(1);
		CountDownLatch doneSignal = new CountDownLatch(numConsumers);
		List<ConsumedMessageCounter> consumedMessageCounters = new ArrayList<ConsumedMessageCounter>(numConsumers);

		try {
			// Inicializar la recepción y envío de los mensajes
			connection.start();

			for (int i = 1; i <= numConsumers; i++) { // create consumers and start threads
				ConsumedMessageCounter counter = new ConsumedMessageCounter(
						new Consumer("Consumer " + i, session, queue), startSignal, doneSignal);
				consumedMessageCounters.add(counter);
				new Thread(counter).start();
			} // for

			Producer producer = new Producer("Producter", session, queue);
			startSignal.countDown(); // let all threads proceed

			final int numMessages = 5;
			for (int i = 1; i <= numMessages; i++) {
				producer.send(String.format("Hello World! (%d)", i));
				Thread.sleep(500);
			}

			doneSignal.await(); // wait for all to finish

			int total = 0;
			for(ConsumedMessageCounter counter: consumedMessageCounters) {
				assertTrue(counter.getCount() <= numMessages);
				total += counter.getCount();
			} // for
			
			assertEquals(numMessages, total);
			
			// Parar el envío y recepción de mensajes
			connection.stop();

		} finally {
			session.close();
			connection.close();
		}
	}

	public void testWhenConsumersAreCreatedAfterSending() throws JMSException, InterruptedException {
		final int numConsumers = 2;
		CountDownLatch startSignal = new CountDownLatch(1);
		CountDownLatch doneSignal = new CountDownLatch(numConsumers);
		List<ConsumedMessageCounter> consumedMessageCounters = new ArrayList<ConsumedMessageCounter>(numConsumers);

		try {
			// Inicializar la recepción y envío de los mensajes
			connection.start();

			final int numMessages = 5;
			Producer producer = new Producer("Producter", session, queue);
			for (int i = 1; i <= numMessages; i++) {
				producer.send(String.format("Hello World! (%d)", i));
				Thread.sleep(500);
			}

			startSignal.countDown(); // let all threads proceed
			for (int i = 1; i <= numConsumers; i++) { // create consumers and start threads
				ConsumedMessageCounter counter = new ConsumedMessageCounter(
						new Consumer("Consumer " + i, session, queue), startSignal, doneSignal);
				consumedMessageCounters.add(counter);
				new Thread(counter).start();
			} // for

			doneSignal.await(); // wait for all to finish

			int total = 0;
			for(ConsumedMessageCounter counter: consumedMessageCounters) {
				assertTrue(counter.getCount() <= numMessages);
				total += counter.getCount();
			} // for
			
			assertEquals(numMessages, total);
			
			// Parar el envío y recepción de mensajes
			connection.stop();

		} finally {
			session.close();
			connection.close();
		}
	}
}