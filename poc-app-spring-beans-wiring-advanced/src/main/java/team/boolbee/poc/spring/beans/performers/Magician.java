package team.boolbee.poc.spring.beans.performers;

import team.boolbee.poc.spring.beans.exceptions.PerformanceException;
import team.boolbee.poc.spring.beans.instruments.MagicBox;

public class Magician implements Performer {

	private MagicBox magicBox;
	private String magicWords;
	
	@Override
	public void perform() throws PerformanceException {
		System.out.println(magicWords);
		System.out.println("The magic box contains...");
		System.out.println(magicBox.getContents());
	}

	public void setMagicBox(MagicBox magicBox) {
		this.magicBox = magicBox;
	}

	public void setMagicWords(String magicWords) {
		this.magicWords = magicWords;
	}
}