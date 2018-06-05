package team.boolbee.poc.logging;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class JCLTest extends AbstractDependencyInjectionSpringContextTests {

	public void testLogging() {
		new LogRegistrar().register("MENSAJE1");
			try {
				Thread.sleep(50000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			new LogRegistrar().register("MENSAJE2");
	}
}