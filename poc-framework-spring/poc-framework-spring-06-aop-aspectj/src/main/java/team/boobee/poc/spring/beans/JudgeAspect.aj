package team.boobee.poc.spring.beans;

public aspect JudgeAspect {

	private CriticismEngine criticismEngine;
	
	public JudgeAspect() {}
	
	pointcut performance(): execution(* perform(..));
	
	after() returning(): performance() {
		System.out.println(criticismEngine.getCriticism());
	}
	
	public void setCriticismEngine(CriticismEngine criticismEngine) {
		this.criticismEngine = criticismEngine;
	}
}