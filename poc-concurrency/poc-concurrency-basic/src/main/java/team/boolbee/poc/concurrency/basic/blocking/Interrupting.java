package team.boolbee.poc.concurrency.basic.blocking;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Interrupting {

	private static Log logger = LogFactory.getLog(Interrupting.class);
	
	private static ExecutorService exec = Executors.newCachedThreadPool();
	
	public static void test(Runnable r) throws InterruptedException {
		Future<?> f = exec.submit(r);
		TimeUnit.MILLISECONDS.sleep(100);
		logger.info("Interrupting " + r.getClass().getSimpleName());
		f.cancel(true); // Interrumpe si se está ejecutando
		logger.info("Interrupt sent to " + r.getClass().getSimpleName());
	}
	
	public static void main(String[] args) throws Exception {
		test(new SleepBlocked());
		test(new IOBlocked(System.in));
		test(new SynchronizedBlocked());
		TimeUnit.SECONDS.sleep(3);
		logger.info("Aborting with System.exit(0)");
		System.exit(0); // Puesto que las 2 últimas interrupciones fallaron
	}
}