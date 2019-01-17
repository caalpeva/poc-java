package team.boolbee.poc.spring.ws.soap;

import java.util.List;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.xpath.XPath;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.ws.server.endpoint.AbstractJDomPayloadEndpoint;

import team.boolbee.poc.spring.ws.model.Card;
import team.boolbee.poc.spring.ws.model.Face;
import team.boolbee.poc.spring.ws.model.PokerHand;
import team.boolbee.poc.spring.ws.model.PokerHandType;
import team.boolbee.poc.spring.ws.model.Suit;
import team.boolbee.poc.spring.ws.service.PokerHandEvaluator;

public class EvaluateHandJDomEndpoint extends AbstractJDomPayloadEndpoint implements InitializingBean {

	private Namespace namespace;
	private XPath cardsXPath;
	private XPath suitXPath;
	private XPath faceXPath;

	public void afterPropertiesSet() throws Exception {
		namespace = Namespace.getNamespace("poker", "http://www.springinaction.com/poker/schemas");
		cardsXPath = XPath.newInstance("/poker:EvaluateHandRequest/poker.card");
		cardsXPath.addNamespace(namespace);
		suitXPath = XPath.newInstance("poker:suit");
		suitXPath.addNamespace(namespace);
		faceXPath = XPath.newInstance("poker:face");
		faceXPath.addNamespace(namespace);
	}

	@Override
	protected Element invokeInternal(Element element) throws Exception {
		Card[] cards = extractCardsFromRequest(element);
		PokerHand pokerHand = new PokerHand();
		pokerHand.setCards(cards);
		
		return createResponse(pokerHandEvaluator.evaluateHand(pokerHand));
	}

	@SuppressWarnings("rawtypes")
	private Card[] extractCardsFromRequest(Element element) throws JDOMException {
		Card[] cards = new Card[5];

		List cardElements = cardsXPath.selectNodes(element);
		for (int i = 0; i < cardElements.size(); i++) {
			Element cardElement = (Element) cardElements.get(i);
			Suit suit = Suit.valueOf(suitXPath.valueOf(cardElement));
			Face face = Face.valueOf(faceXPath.valueOf(cardElement));
			cards[i] = new Card();
			cards[i].setSuit(suit);
			cards[i].setFace(face);
		} // for

		return cards;
	}
	
	private Element createResponse(PokerHandType handType) {
		Element responseElement = new Element("EvaluateHandResponse", namespace);
		responseElement.addContent(new Element("handName", namespace).setText(handType.name()));
		return responseElement;
	}
	
	private PokerHandEvaluator pokerHandEvaluator;
	public void setPokerHandEvaluator(PokerHandEvaluator pokerHandEvaluator) {
		this.pokerHandEvaluator = pokerHandEvaluator;
	}
}