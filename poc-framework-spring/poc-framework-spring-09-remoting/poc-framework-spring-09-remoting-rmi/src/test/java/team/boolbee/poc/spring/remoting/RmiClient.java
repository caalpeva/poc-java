package team.boolbee.poc.spring.remoting;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import team.boolbee.poc.spring.remoting.model.Citation;
import team.boolbee.poc.spring.remoting.model.CitationService;


public class RmiClient extends AbstractDependencyInjectionSpringContextTests {
	
	@Override
	protected String[] getConfigLocations() {
		return new String[] { "rmi-client.xml" };
	}
	
	public void testClient() {

      CitationService cs = (CitationService) getApplicationContext().getBean("citationService");

      Citation[] citations = cs.getCitationsForVehicle("TX", "J55DNY");

      for (int i = 0; i < citations.length; i++) {
         Citation citation = citations[i];
         System.out.println(i + ".  " + citation.getViolationCode() + " - "
                           + citation.getDescription());
      }
   }
}
