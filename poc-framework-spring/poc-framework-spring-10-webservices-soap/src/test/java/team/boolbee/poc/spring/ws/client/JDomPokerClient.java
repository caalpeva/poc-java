package team.boolbee.poc.spring.ws.client;

import java.io.IOException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.transform.JDOMResult;
import org.jdom.transform.JDOMSource;
import org.springframework.ws.client.core.WebServiceTemplate;

import team.boolbee.poc.spring.ws.model.Card;
import team.boolbee.poc.spring.ws.model.PokerHandType;

public class JDomPokerClient implements PokerClient {

	public PokerHandType evaluateHand(Card[] cards) throws IOException {
		Namespace namespace = Namespace.getNamespace("poker", "http://www.springinaction.com/poker/schemas");
		Element requestElement = new Element("EvaluateHandRequest", namespace);
		//requestElement.setNamespace(namespace);

		// Construct message
		Document document = new Document(requestElement);
		for (int i = 0; i < cards.length; i++) {
			Element suitElement = new Element("suit", namespace);
			suitElement.setText(cards[i].getSuit().name());

			Element faceElement = new Element("face", namespace);
			faceElement.setText(cards[i].getFace().name());

			Element cardElement = new Element("card", namespace);
			cardElement.addContent(suitElement);
			cardElement.addContent(faceElement);

			document.getRootElement().addContent(i, cardElement);
		} // for

		// Send message
		JDOMSource requestSource = new JDOMSource(document);
		JDOMResult result = new JDOMResult();
		webServiceTemplate.sendSourceAndReceiveToResult(requestSource, result);

		// Parse result
		Document resultDocument = result.getDocument();
		Element responseElement = resultDocument.getRootElement();
		Element handNameElement = responseElement.getChild("handName", namespace);
		return PokerHandType.valueOf(handNameElement.getText());
	}

	// injected
	private WebServiceTemplate webServiceTemplate;

	public void setWebServiceTemplate(WebServiceTemplate webServiceTemplate) {
		this.webServiceTemplate = webServiceTemplate;
	}
}