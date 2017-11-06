package team.boolbee.poc.spring.beans.performers;

import org.springframework.beans.factory.annotation.Configurable;

import team.boolbee.poc.spring.beans.exceptions.PerformanceException;
import team.boolbee.poc.spring.beans.instruments.Instrument;

@Configurable("pianist")
public class Instrumentalist implements Performer {

	private Instrument instrument;
	private String song;

	public Instrumentalist() {
	}

	@Override
	public void perform() throws PerformanceException {
		System.out.println("Instrument id: " + instrument.toString());
		System.out.print("Playing " + song + " : ");
		instrument.play();
	}

	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}

	public void setSong(String song) {
		this.song = song;
	}
	
	public void tuneInstrument() {
		System.out.println("Tuning instrument: " + instrument.toString());
		//instrument.tune();
	}
	
	public void cleanInstrument() {
		System.out.println("Cleaning instrument: " + instrument.toString());
		//instrument.clean();
	}
}