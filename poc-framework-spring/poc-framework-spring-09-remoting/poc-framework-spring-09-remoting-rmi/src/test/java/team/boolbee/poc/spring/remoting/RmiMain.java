package team.boolbee.poc.spring.remoting;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import team.boolbee.poc.spring.remoting.model.CitationService;

public class RmiMain {
   public static void main(String[] args) {
      String citatinUrl = "rmi://localhost/CitationService";
      try {
		CitationService citationService = (CitationService) Naming.lookup(citatinUrl);
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NotBoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
}
