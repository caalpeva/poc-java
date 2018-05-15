package team.boolbee.poc.spring.beans.others;

public class Lime implements ILime {

	public Lime() {}

	@Override
	public void drink() {
		System.out.println("Called the doctor woke him up! (Java)");
	}
}