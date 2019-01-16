package team.boolbee.poc.spring.webservices.soap;

import team.boolbee.poc.spring.webservices.model.Card;

public class EvaluateHandRequest {
	private Card[] hand;

	public EvaluateHandRequest() {
	}

	public Card[] getHand() {
		return hand;
	}

	public void setHand(Card[] cards) {
		this.hand = cards;
	}
}
