package team.boolbee.poc.spring.ws.soap;

import team.boolbee.poc.spring.ws.model.PokerHandType;

public class EvaluateHandResponse {
	private PokerHandType pokerHandType;

	public EvaluateHandResponse() {
		this(PokerHandType.NONE);
	}

	public EvaluateHandResponse(PokerHandType pokerHandType) {
		this.pokerHandType = pokerHandType;
	}

	public PokerHandType getPokerHand() {
		return this.pokerHandType;
	}

	public void setPokerHand(PokerHandType pokerHandType) {
		this.pokerHandType = pokerHandType;
	}
}