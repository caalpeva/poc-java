package team.boolbee.poc.spring.beans.performers;

import team.boolbee.poc.spring.beans.exceptions.PerformanceException;

public interface Performer {
	public void perform() throws PerformanceException;
}
