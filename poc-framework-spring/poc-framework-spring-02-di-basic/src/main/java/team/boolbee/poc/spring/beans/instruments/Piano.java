package team.boolbee.poc.spring.beans.instruments;

public class Piano implements Instrument {

	@Override
	public void play() {
		System.out.println("PLINK PLINK PLINK");
	}
}