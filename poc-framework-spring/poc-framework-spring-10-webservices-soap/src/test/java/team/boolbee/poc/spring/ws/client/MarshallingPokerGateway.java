package team.boolbee.poc.spring.ws.client;

import java.io.IOException;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import team.boolbee.poc.spring.ws.model.Card;
import team.boolbee.poc.spring.ws.model.PokerHandType;
import team.boolbee.poc.spring.ws.soap.EvaluateHandRequest;
import team.boolbee.poc.spring.ws.soap.EvaluateHandResponse;

public class MarshallingPokerGateway extends WebServiceGatewaySupport implements PokerClient {

	public PokerHandType evaluateHand(Card[] cards) throws IOException {
		EvaluateHandRequest request = new EvaluateHandRequest();
		request.setHand(cards);
		EvaluateHandResponse response = (EvaluateHandResponse) getWebServiceTemplate().marshalSendAndReceive(request);
		return response.getPokerHand();
	}
}