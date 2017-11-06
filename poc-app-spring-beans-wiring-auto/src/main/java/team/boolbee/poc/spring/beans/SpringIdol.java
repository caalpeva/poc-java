package team.boolbee.poc.spring.beans;

//import java.util.Collection;

import team.boolbee.poc.spring.beans.performers.Performer;

public class SpringIdol implements TalentCompetition {

//	private Collection<Performer> performers;
	private Performer[] performers;
	
	@Override
	public void run() {
		for(Performer performer: performers) {
			performer.perform();
			System.out.println("-----------------------");
		} // for
	}

//	public void setPerformers(Collection<Performer> performers) {
//		this.performers = performers;
//	}

	public void setPerformers(Performer[] performers) {
		this.performers = performers;
	}
}