package team.boolbee.poc.spring.beans.performers;

import team.boolbee.poc.spring.beans.exceptions.PerformanceException;

public class Juggler implements Performer {
	
	private int beanBags = 3;

	public Juggler() {}
	
	public Juggler(int beanBags) {
		this.beanBags = beanBags;
	}
	
	@Override
	public void perform() throws PerformanceException {
		System.out.println("JUGGLING " + beanBags + " BEANBAGS");
	}
}