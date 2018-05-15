package team.boolbee.poc.spring.beans.performers;

import team.boolbee.poc.spring.beans.exceptions.PerformanceException;
import team.boolbee.poc.spring.beans.others.Poem;

public class PoeticJuggler extends Juggler {

	private Poem poem;
	
	public PoeticJuggler(Poem poem) {
		super();
		this.poem = poem;
	}
	
	public PoeticJuggler(int beanBags, Poem poem) {
		super(beanBags);
		this.poem = poem;
	}
	
	@Override
	public void perform() throws PerformanceException {
		super.perform();
		System.out.println("While reciting...");
		poem.recite();
	}
}
