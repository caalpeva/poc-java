package team.boolbee.poc.spring.beans.performers;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import team.boolbee.poc.spring.beans.exceptions.PerformanceException;
import team.boolbee.poc.spring.beans.instruments.Instrument;

public class Instrumentalist2 extends Instrumentalist implements InitializingBean, DisposableBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		tuneInstrument();
	}
	
	@Override
	public void destroy() throws Exception {
		cleanInstrument();
	}
}