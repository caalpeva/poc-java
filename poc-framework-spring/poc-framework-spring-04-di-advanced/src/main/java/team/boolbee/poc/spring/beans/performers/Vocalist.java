package team.boolbee.poc.spring.beans.performers;

import team.boolbee.poc.spring.beans.exceptions.PerformanceException;
import team.boolbee.poc.spring.beans.instruments.Instrument;

public class Vocalist implements Performer {	

	private String song;
	
	public Vocalist() {}
	
	@Override
	public void perform() throws PerformanceException {
		 System.out.println("Playing " + song + " (a cappella)");
	}

	public void setSong(String song) {
		this.song = song;
	}
}