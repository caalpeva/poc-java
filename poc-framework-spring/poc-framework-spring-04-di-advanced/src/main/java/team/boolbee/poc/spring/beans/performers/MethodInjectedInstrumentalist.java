package team.boolbee.poc.spring.beans.performers;

import team.boolbee.poc.spring.beans.exceptions.PerformanceException;
import team.boolbee.poc.spring.beans.instruments.Instrument;

public abstract class MethodInjectedInstrumentalist implements Performer {

	private String song;

	public MethodInjectedInstrumentalist() {
	}

	@Override
	public void perform() throws PerformanceException {
		Instrument instrument = getInstrument();
		System.out.println("Instrument id: " + instrument.toString());
		System.out.print("Playing " + song + " : ");
		instrument.play();
		System.out.println("(The instrument is broken)");
		instrument = getInstrument();
		System.out.println("Change instrument (" + instrument.toString() + ")");
		System.out.print("Continue playing " + song + " : ");
		instrument.play();
	}

	public void setSong(String song) {
		this.song = song;
	}

	public abstract Instrument getInstrument();
}