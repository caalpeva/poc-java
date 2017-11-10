package team.boobee.poc.spring.beans;

public class CriticismEngine {

	public String[] criticismPool;
	
	public CriticismEngine() {}
	
	public String getCriticism() {
		int i = (int) (Math.random() * criticismPool.length);
		return criticismPool[i];
	}
	
	public void setCriticismPool(String[] criticismPool) {
		this.criticismPool = criticismPool;
	}
}