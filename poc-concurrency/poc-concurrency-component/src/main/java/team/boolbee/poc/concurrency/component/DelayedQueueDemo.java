package team.boolbee.poc.concurrency.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

class DelayedTask implements Runnable, Delayed {
	protected static List<DelayedTask> sequence = new ArrayList<DelayedTask>();
	private static int counter = 0;
	private final int id = counter++;
	private final int delay;
	private final long trigger;
	
	public DelayedTask(int delayInMilliseconds) {
		delay = delayInMilliseconds;
		//trigger = System.currentTimeMillis() + delay;
		trigger = System.nanoTime() + TimeUnit.NANOSECONDS.convert(delay, TimeUnit.MILLISECONDS);
		sequence.add(this);
	}
	
	public long getDelay(TimeUnit unit) {
		//return unit.convert(trigger - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
		return unit.convert(trigger - System.nanoTime(), TimeUnit.NANOSECONDS);
	}
	
	public int compareTo(Delayed arg) {
		DelayedTask that = (DelayedTask) arg;
		if (trigger < that.trigger) {
			return -1;
		}
		if (trigger > that.trigger) {
			return 1;
		}
		return 0;
	}

	public String summary() {
		return String.format("(%s: %s)", id, delay);
	}
	
	public void run() {
		DelayedQueueDemo.logger.info(this + "completed");
	}
	
	@Override
	public String toString() {
		return String.format("[%s] Task %s ", delay, id);
	}
}

class EndSentinel extends DelayedTask {
	
	private ExecutorService exec;
	
	public EndSentinel(int delay, ExecutorService exec) {
		super(delay);
		this.exec = exec;
	}
	
	@Override
	public void run() {
		for(DelayedTask pt: sequence) {
			DelayedQueueDemo.logger.info(pt.summary());
		} // for
		
		DelayedQueueDemo.logger.info(this + "Calling shutdownNow()");
		exec.shutdownNow();
	}
}
	
class DelayedTaskConsumer implements Runnable {
	private DelayQueue<DelayedTask> queue;
	
	public DelayedTaskConsumer(DelayQueue<DelayedTask> queue) {
		this.queue = queue;
	}
	
	public void run() {
		try {
			while(!Thread.interrupted()) {
				DelayedQueueDemo.logger.info("Waiting for task");
				queue.take().run(); // Ejecutar tarea con la hebra actual
			} // while
		} catch(InterruptedException e) {
			// Ignore
		}
		
		DelayedQueueDemo.logger.info("Finished DelayedTaskConsumer");
	}
}

public class DelayedQueueDemo {

	static Log logger = LogFactory.getLog(DelayedQueueDemo.class);
	static Random random = new Random();
	
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		DelayQueue<DelayedTask> queue = new DelayQueue<DelayedTask>();
		
		// Rellenar con tareas que tengan retardos aleatorios
		for(int i = 0; i < 20; i++) {
			queue.put(new DelayedTask(random.nextInt(5000)));
		} // for
		
		// Establecer el punto de detención
		queue.put(new EndSentinel(5000, exec));
		
		exec.execute(new DelayedTaskConsumer(queue));
	}
}