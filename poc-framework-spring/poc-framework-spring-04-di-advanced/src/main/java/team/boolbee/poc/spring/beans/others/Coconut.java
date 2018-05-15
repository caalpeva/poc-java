package team.boolbee.poc.spring.beans.others;

public class Coconut implements ICoconut {

	private ILime lime;
	
	public Coconut() {}
	
	public void drinkThemBothUp() {
		System.out.println("(Java)");
		System.out.println("You put the lime in the coconut...");
		System.out.println("and drink them both up...");
		System.out.println("You put the lime in the coconut...");
		lime.drink();
	}

	public ILime getLime() {
		return lime;
	}

	public void setLime(ILime lime) {
		this.lime = lime;
	}
}