package team.boolbee.poc.spring.beans.exceptions;

@SuppressWarnings("serial")
public class PerformanceException extends RuntimeException {
	public PerformanceException() {
		super();
	}

	public PerformanceException(String message) {
		super(message);
	}
}
