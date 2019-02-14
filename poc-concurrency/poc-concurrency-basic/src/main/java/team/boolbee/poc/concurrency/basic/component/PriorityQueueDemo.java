package team.boolbee.poc.concurrency.basic.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

class PrioritizedTask implements Runnable, Comparable<PrioritizedTask> {
	protected static List<PrioritizedTask> sequence = new ArrayList<PrioritizedTask>();
	private static int counter = 0;
	private final int id = counter++;
	private final int priority;
	
	public PrioritizedTask(int priority) {
		this.priority = priority;
		sequence.add(this);
	}
	
	public int compareTo(PrioritizedTask o) {
		return priority < o.priority? 1:
			(priority > o.priority? -1: 0);
	}
	
	public String summary() {
		return String.format("(%s: %s)", id, priority);
	}
	
	public void run() {
		try {
			TimeUnit.MILLISECONDS.sleep(new Random().nextInt(250));
		} catch (InterruptedException e) {
			// Ignore
		}
		PriorityQueueDemo.logger.info(this + "completed");
	}
	
	@Override
	public String toString() {
		return String.format("[%s] Task %s ", priority, id);
	}
}

class PrioritizedEndSentinel extends PrioritizedTask {
	
	private ExecutorService exec;
	
	public PrioritizedEndSentinel(ExecutorService exec) {
		super(-1); // La menor prioridad 
		this.exec = exec;
	}
	
	@Override
	public void run() {
		for(PrioritizedTask pt: sequence) {
			PriorityQueueDemo.logger.info(pt.summary());
		} // for
		
		PriorityQueueDemo.logger.info(this + "Calling shutdownNow()");
		exec.shutdownNow();
	}
}

class PrioritizedTaskProducer implements Runnable {
	private Random random = new Random();
	private PriorityBlockingQueue<Runnable> queue;
	private ExecutorService exec;
	
	public PrioritizedTaskProducer(PriorityBlockingQueue<Runnable> queue, ExecutorService exec) {
		this.queue = queue;
		this.exec = exec;
	}
	
	public void run() {
		// Rellenar con tareas que tengan prioridades aleatorias
		for(int i = 0; i < 20; i++) {
			queue.add(new PrioritizedTask(random.nextInt(10)));
			Thread.yield();
		} // for
		
		try {
			// Introducir tareas de prioridad máxima
			for(int i = 0; i < 10; i++) {
				TimeUnit.MILLISECONDS.sleep(250);
				queue.add(new PrioritizedTask(10));
			} // for
			
			// Introducir tareas, primero las de menor prioridad
			for(int i = 0; i < 10; i++) {
				queue.add(new PrioritizedTask(1));
			} // for
			
			// Un centinela para detener todas las tareas
			queue.add(new PrioritizedEndSentinel(exec));
		} catch(InterruptedException e) {
			// Ignore
		}
		
		PriorityQueueDemo.logger.info("Finished PrioritizedTaskProducer");
	}
}

class PrioritizedTaskConsumer implements Runnable {
	private PriorityBlockingQueue<Runnable> queue;
	
	public PrioritizedTaskConsumer(PriorityBlockingQueue<Runnable> queue) {
		this.queue = queue;
	}
	
	public void run() {
		try {
			while(!Thread.interrupted()) {
				PriorityQueueDemo.logger.info("Waiting for task");
				queue.take().run(); // Ejecutar tarea con la hebra actual
			} // while
		} catch(InterruptedException e) {
			// Ignore
		}
		
		PriorityQueueDemo.logger.info("Finished PrioritizedTaskConsumer");
	}
}

public class PriorityQueueDemo {

	static Log logger = LogFactory.getLog(PriorityQueueDemo.class);
	
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		PriorityBlockingQueue<Runnable> queue = new PriorityBlockingQueue<Runnable>();
		exec.execute(new PrioritizedTaskProducer(queue, exec));
		exec.execute(new PrioritizedTaskConsumer(queue));
	}
}