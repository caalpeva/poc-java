package team.boolbee.poc.spring.ws.client;

import java.io.IOException;

import org.springframework.ws.client.core.WebServiceTemplate;

import team.boolbee.poc.spring.ws.model.Card;
import team.boolbee.poc.spring.ws.model.PokerHandType;
import team.boolbee.poc.spring.ws.soap.EvaluateHandRequest;
import team.boolbee.poc.spring.ws.soap.EvaluateHandResponse;

public class MarshallingPokerClient implements PokerClient {

	public PokerHandType evaluateHand(Card[] cards) throws IOException {
		EvaluateHandRequest request = new EvaluateHandRequest();
		request.setHand(cards);
		EvaluateHandResponse response = (EvaluateHandResponse) webServiceTemplate.marshalSendAndReceive(request);
		return response.getPokerHand();
	}

	// injected
	private WebServiceTemplate webServiceTemplate;

	public void setWebServiceTemplate(WebServiceTemplate webServiceTemplate) {
		this.webServiceTemplate = webServiceTemplate;
	}
}