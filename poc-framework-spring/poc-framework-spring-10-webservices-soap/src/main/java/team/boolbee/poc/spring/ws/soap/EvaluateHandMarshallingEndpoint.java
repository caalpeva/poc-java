package team.boolbee.poc.spring.ws.soap;

import org.springframework.ws.server.endpoint.AbstractMarshallingPayloadEndpoint;

import team.boolbee.poc.spring.ws.model.PokerHand;
import team.boolbee.poc.spring.ws.service.PokerHandEvaluator;

public class EvaluateHandMarshallingEndpoint extends AbstractMarshallingPayloadEndpoint {

	@Override
	protected Object invokeInternal(Object object) throws Exception {
		EvaluateHandRequest request = (EvaluateHandRequest) object;
		PokerHand pokerHand = new PokerHand();
		pokerHand.setCards(request.getHand());
		
		return new EvaluateHandResponse(pokerHandEvaluator.evaluateHand(pokerHand));
	}
	
	private PokerHandEvaluator pokerHandEvaluator;
	public void setPokerHandEvaluator(PokerHandEvaluator pokerHandEvaluator) {
		this.pokerHandEvaluator = pokerHandEvaluator;
	}
}