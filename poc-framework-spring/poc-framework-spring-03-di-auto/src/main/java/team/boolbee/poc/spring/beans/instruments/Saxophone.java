package team.boolbee.poc.spring.beans.instruments;

public class Saxophone implements Instrument {

	@Override
	public void play() {
		System.out.println("TOOT TOOT TOOT");
	}
}