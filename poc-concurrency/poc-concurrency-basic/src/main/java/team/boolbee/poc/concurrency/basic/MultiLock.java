package team.boolbee.poc.concurrency.basic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MultiLock {

	private static Log logger = LogFactory.getLog(MultiLock.class);
	
	public synchronized void f1(int count) {
		if (count-- > 0) {
			logger.info("f1 calling f2() with count " + count);
			f2(count);
		}
	}
	
	public synchronized void f2(int count) {
		if (count-- > 0) {
			logger.info("f2 calling f1() with count " + count);
			f1(count);
		}
	}
	
	public static void main(String[] args) throws Exception {
		final MultiLock multiLock = new MultiLock();
		new Thread() {
			public void run() {
				multiLock.f1(10);
			};
		}.start();
	}
	
	// Una hebra puede volver a adquirir multiples veces el mismo bloqueo.
	// Esto tiene sentido, porque una tarea debe ser capaz de invocar otros métodos
	// sincronizados contenidos dentro del mismo objeto, ya que dicha tarea ya posee el bloqueo.
}
