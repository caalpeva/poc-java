package team.boolbee.poc.jms.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import junit.framework.TestCase;

public class JmsQueueTest extends TestCase {

	private Connection connection;
	private Queue queue;

	@Override
	protected void setUp() throws Exception {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		connection = connectionFactory.createConnection();
		queue = new ActiveMQQueue("myQueue");
	}

	public void testJmsQueueFistStage() {
		SynchConsumer consumer1 = null, consumer2 = null;
		Producer producer = null;

		try {
			// Preparar el productor y consumidor al Queue
			consumer1 = new SynchConsumer("Consumer 1", connection, queue);
			consumer2 = new SynchConsumer("Consumer 2", connection, queue);
			producer = new Producer("Producter", connection, queue);

			// Inicializar la recepción y envío de los mensajes
			connection.start();

			Thread thread = new Thread(consumer1);
			thread.start();
			
			thread = new Thread(consumer2);
			thread.start();
			
			// Empezar a enviar mensajes en el Queue (y a recibirlos)
			thread = new Thread(producer);
			thread.start();
			thread.join(); // Esperar a que el enviador termine de enviar
							// mensajes

			Thread.sleep(5000);
			
			// Parar el envío y recepción de mensajes
			connection.stop();
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				consumer1.close();
				consumer2.close();
				producer.close();
				connection.close();
			} catch (JMSException e) {
				// Do nothing
			}
		}
	}
	
	public void testJmsQueueSecondStage() {
		SynchConsumer consumer1 = null;
		Producer producer = null;

		try {
			// Preparar el productor y consumidor al Queue
			consumer1 = new SynchConsumer("Consumer 1", connection, queue);
			producer = new Producer("Producter", connection, queue);

			// Inicializar la recepción y envío de los mensajes
			connection.start();

			// Empezar a enviar mensajes en el Queue (y a recibirlos)
			Thread thread = new Thread(producer);
			thread.start();
			thread.join(); // Esperar a que el enviador termine de enviar
							// mensajes

			thread = new Thread(consumer1);
			thread.start();
			
			Thread.sleep(5000);
			
			// Parar el envío y recepción de mensajes
			connection.stop();
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				consumer1.close();
				producer.close();
				connection.close();
			} catch (JMSException e) {
				// Do nothing
			}
		}
	}
}
