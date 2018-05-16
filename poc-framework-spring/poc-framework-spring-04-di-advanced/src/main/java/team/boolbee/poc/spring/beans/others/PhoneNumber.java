package team.boolbee.poc.spring.beans.others;

public class PhoneNumber {
	private String areaCode;
	private String prefix;
	private String number;
	
	public PhoneNumber() {}
	
	public PhoneNumber(String areaCode, String prefix, String number) {
		this.areaCode = areaCode;
		this.prefix = prefix;
		this.number = number;
	}
	
	@Override
	public String toString() {
		return String.format("%s %s %s", areaCode, prefix, number);
	}
}