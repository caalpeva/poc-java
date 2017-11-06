package team.boolbee.poc.spring.beans.instruments;

public class Guitar implements Instrument {
	public Guitar() {}

	public void play() {
		System.out.println("STRUM STRUM STRUM");
	}
}