package team.boolbee.poc.spring.beans.performers;

import java.util.Map;

import team.boolbee.poc.spring.beans.exceptions.PerformanceException;
import team.boolbee.poc.spring.beans.instruments.Instrument;

public class OneManBand implements Performer {

	private Map<String, Instrument> instruments;

	@Override
	public void perform() throws PerformanceException {
		for (String key: instruments.keySet()) {
			System.out.print(key + " : ");
			Instrument instrument = instruments.get(key);
			instrument.play();
		}
	}

	public void setInstruments(Map<String, Instrument> instruments) {
		this.instruments = instruments;
	}
}