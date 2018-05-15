package team.boolbee.poc.spring.beans.performers;

import team.boolbee.poc.spring.beans.exceptions.PerformanceException;
import team.boolbee.poc.spring.beans.instruments.Instrument;

public class Instrumentalist implements Performer {

	private Instrument instrument;
	private String song;
	
	public Instrumentalist() {}
	
	@Override
	public void perform() throws PerformanceException {
		 System.out.print("Playing " + song + " : ");
		 instrument.play();
	}

	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}

	public void setSong(String song) {
		this.song = song;
	}
}