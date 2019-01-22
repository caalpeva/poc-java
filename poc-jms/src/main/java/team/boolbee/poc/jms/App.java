package team.boolbee.poc.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;

public class App {

	private static final int DEFAULT_PORT = 61616;

	private static String destinationType;
	private static String performerType;
	
	private static int numMessages = 1;
	private static String messageContent;

	public static void main(String[] args) throws JMSException, InterruptedException {
		checkArguments(args);

		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:" + DEFAULT_PORT);
		Connection connection = connectionFactory.createConnection();

		System.out.println("Destination type is " + destinationType);
		Destination destination = destinationType.equals("queue")
				? new ActiveMQQueue("pocQueue")
				: new ActiveMQTopic("pocTopic");

		Session session = null;
		try {
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			if (performerType.equals("producer")) {
				sendMessages(session, destination);
			} else {
				receiveMessages(connection, session, destination);
			}
		} finally {
			session.close();
			connection.close();
		}
	}

	private static void checkArguments(String[] args) {
		if (args.length < 2 || args.length > 3) {
			System.err.println("Incorrect number of arguments");
			showCommandFormatAndExit(2);
		}

		destinationType = args[0];
		if (!(destinationType.equals("queue") || destinationType.equals("topic"))) {
			System.err.println("Argument must be \"queue\" or \"topic\"");
			showCommandFormatAndExit(2);
		}

		performerType = args[1];
		if (!(performerType.equals("consumer") || performerType.equals("producer"))) {
			System.err.println("Argument must be \"consumer\" or \"producer\"");
			showCommandFormatAndExit(2);
		}

		messageContent = "Hello World!";
		if (args.length == 3) {
			if (performerType.equals("consumer")) {
				System.err.println("Incorrect number of arguments for consumer");
				showCommandFormatAndExit(2);
			}

			try {
				numMessages = Integer.parseInt(args[2]);
			} catch (NumberFormatException e) {
				messageContent = args[2];
			}
		}
	}

	private static void sendMessages(Session session, Destination destination) throws InterruptedException, JMSException {
		String producerName = destinationType.equals("queue")? "Producer": "Publisher";
		Producer producer = new Producer(producerName, session, destination);
		try {
			for (int i = 1; i <= numMessages; ++i) {
				String messageText = (numMessages > 1) ? String.format("%s (%d)", messageContent, i) : messageContent;

				// message.getJMSTimestamp()
				System.out.println("Sending message: " + messageText);
				producer.send(messageText);
				Thread.sleep(1000);
			} // for
		} finally {
			producer.close();
		}
	}

	private static void receiveMessages(Connection connection, Session session, Destination destination) throws JMSException {
		String consumerName = destinationType.equals("queue")? "Consumer": "Subscriber";
		Consumer consumer = new Consumer(consumerName, session, destination);
		try {
			connection.start();
			while (true) {
				consumer.receive();
			} // while
		} finally {
			connection.stop();
			consumer.close();
		}
	}
	
	private static void showCommandFormatAndExit(int status) {
		System.err.println("Syntax:\n\tqueue|topic consumer\n\tqueue|topic producer [text|messages number]");
		System.exit(2);
	}
}
