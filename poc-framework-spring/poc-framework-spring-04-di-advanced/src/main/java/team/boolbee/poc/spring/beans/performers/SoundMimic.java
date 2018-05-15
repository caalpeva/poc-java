package team.boolbee.poc.spring.beans.performers;

import java.util.Iterator;
import java.util.Properties;

import team.boolbee.poc.spring.beans.exceptions.PerformanceException;

public class SoundMimic implements Performer {

	private Properties sounds;

	@Override
	public void perform() throws PerformanceException {
		for (Iterator it = sounds.keySet().iterator(); it.hasNext();) {
			String key = (String) it.next();
			System.out.println(key + " : " + sounds.getProperty(key));
		}
	}

	public void setSounds(Properties sounds) {
		this.sounds = sounds;
	}
}