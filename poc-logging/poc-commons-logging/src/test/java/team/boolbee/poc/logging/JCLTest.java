package team.boolbee.poc.logging;

import junit.framework.TestCase;

public class JCLTest extends TestCase {

	public void testLogging() {
		new LogRegistrar().register("Hola Guapa!! ¿Que hace mi socorrita preferida?");
			try {
				Thread.sleep(50000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			new LogRegistrar().register("Este es un mensaje para mi churritina desde una prueba de correo");
	}
}