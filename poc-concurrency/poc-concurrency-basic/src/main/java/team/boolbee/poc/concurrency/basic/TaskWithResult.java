package team.boolbee.poc.concurrency.basic;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TaskWithResult implements Callable<String> {

	private static Log logger = LogFactory.getLog(TaskWithResult.class);
	
	private int id;
	
	public TaskWithResult(int id) {
		this.id = id;
	}
	
	public String call() throws Exception {
		TimeUnit.SECONDS.sleep(5);
		return "result of TaskWithResult " + id;
	}
	
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		ArrayList<Future<String>> results = new ArrayList<Future<String>>();
		for(int i = 0; i < 10; i++) {
			results.add(exec.submit(new TaskWithResult(i)));
		} // for
		
		for(Future<String> future: results) {
			try {
				// Se puede consultar el objeto Future con isDone()
				// para ver si se ha completado.
				// Con get() se bloquea hasta que el resultado esté listo
				logger.info(future.get());
			} catch(InterruptedException e) {
				logger.error(e);
			} catch (ExecutionException e) {
				logger.error(e);
			} finally {
				exec.shutdown();
			}
		} // for
	}
}