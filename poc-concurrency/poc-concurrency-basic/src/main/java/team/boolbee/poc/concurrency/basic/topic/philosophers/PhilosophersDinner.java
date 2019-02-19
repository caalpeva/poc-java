package team.boolbee.poc.concurrency.basic.topic.philosophers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PhilosophersDinner {

	private static Log logger = LogFactory.getLog(Philosopher.class);
	
	public static void main(String[] args) throws Exception {
		int ponder = 5;
		if (args.length > 0) {
			ponder = Integer.parseInt(args[0]);
		}
		
		int size = 5;
		if (args.length > 1) {
			size = Integer.parseInt(args[1]);
		}
		
		Chopstick[] sticks = new Chopstick[size];
		for(int i = 0; i < size; i++) {
			sticks[i] = new Chopstick();
		} // for

		ThreadFactory namedThreadFactory = new BasicThreadFactory.Builder()
			     .namingPattern("Philosopher-%d")
			     .build();
		ExecutorService exec = Executors.newCachedThreadPool(namedThreadFactory);
		for(int i = 0; i < size; i++) {
			//exec.execute(new Philosopher(sticks[i], sticks[(i+1) % size], ponder));
			// Generar una hebra con un filósofo zurdo evita el interbloqueo
			if (i < (size - 1)) {
				exec.execute(new Philosopher(sticks[i], sticks[(i+1) % size], ponder));
			} else {
				exec.execute(new Philosopher(sticks[0], sticks[i], ponder));
			}
		} // for
		
		if (args.length == 3 && args[2].equals("timeout")) {
			TimeUnit.SECONDS.sleep(5);
		} else {
			logger.info("Press 'Enter' to quit");
			System.in.read();
		}
		
		exec.shutdownNow();
	}
}
