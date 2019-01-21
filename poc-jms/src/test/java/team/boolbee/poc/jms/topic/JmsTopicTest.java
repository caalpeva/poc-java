package team.boolbee.poc.jms.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;

import junit.framework.TestCase;
import team.boolbee.poc.jms.queue.Producer;
import team.boolbee.poc.jms.queue.SynchConsumer;

public class JmsTopicTest extends TestCase {

	private Connection connection;
	private Topic topic;

	@Override
	protected void setUp() throws Exception {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		connection = connectionFactory.createConnection();
		topic = new ActiveMQTopic("myTestTopic");
	}

	public void testJmsTopicFistStage() {
		Subscriber consumer1 = null, consumer2 = null;
		Publisher producer = null;

		try {
			// Preparar el productor y consumidor al Queue
			consumer1 = new Subscriber("Subscriber 1", connection, topic);
			consumer2 = new Subscriber("Subscriber 2", connection, topic);
			producer = new Publisher("Publisher", connection, topic);

			// Inicializar la recepción y envío de los mensajes
			connection.start();

			// Empezar a enviar mensajes en el Queue (y a recibirlos)
			Thread  thread = new Thread(producer);
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
	
	public void testJmsTopicSecondStage() {
		Subscriber consumer1 = null;
		Publisher producer = null;

		try {
			// Preparar el productor y consumidor al Queue
			producer = new Publisher("Publisher", connection, topic);

			// Inicializar la recepción y envío de los mensajes
			connection.start();

			// Empezar a enviar mensajes en el Queue (y a recibirlos)
			Thread thread = new Thread(producer);
			thread.start();
			thread.join(); // Esperar a que el enviador termine de enviar
							// mensajes

			consumer1 = new Subscriber("Subscriber 1", connection, topic);
			
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