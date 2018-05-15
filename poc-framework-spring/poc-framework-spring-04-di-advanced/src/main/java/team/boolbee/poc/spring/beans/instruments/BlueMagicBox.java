package team.boolbee.poc.spring.beans.instruments;

public class BlueMagicBox implements MagicBox {

	public BlueMagicBox() {
	}
	
	@Override
	public String getContents() {
		return "A beautiful assistant";
	}
}