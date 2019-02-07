package team.boolbee.poc.spring.ws.client;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import team.boolbee.poc.spring.ws.model.Card;
import team.boolbee.poc.spring.ws.model.Face;
import team.boolbee.poc.spring.ws.model.PokerHandType;
import team.boolbee.poc.spring.ws.model.Suit;

public class EvaluateHandEndpointITest extends AbstractDependencyInjectionSpringContextTests {

	@Override
	protected String[] getConfigLocations() {
		return new String[] { "spring-ws-client.xml" }; // "classpath:/team/boolbee/poc/spring/ws/client/spring-ws-client.xml"
	}

	public void testJDomClient() throws Exception {
		PokerClient jDomClient = (PokerClient) applicationContext.getBean("jDomClient");
		assertEquals(PokerHandType.FULL_HOUSE, jDomClient.evaluateHand(createFullHouseHand()));
	}
	
	public void testMarshallingClient() throws Exception {
		PokerClient marshallingClient = (PokerClient) applicationContext.getBean("marshallingClient");
		assertEquals(PokerHandType.FULL_HOUSE, marshallingClient.evaluateHand(createFullHouseHand()));
	}
	
	public void testMarshallingGateway() throws Exception {
		PokerClient marshallingClient = (PokerClient) applicationContext.getBean("marshallingGateway");
		assertEquals(PokerHandType.FULL_HOUSE, marshallingClient.evaluateHand(createFullHouseHand()));
	}

	private static Card[] createFullHouseHand() {
		return new Card[] {
				new Card(Suit.CLUBS, Face.ACE),
				new Card(Suit.HEARTS, Face.ACE),
				new Card(Suit.CLUBS, Face.TWO),
				new Card(Suit.SPADES, Face.TWO),
				new Card(Suit.DIAMONDS, Face.TWO)
			};
	}
}