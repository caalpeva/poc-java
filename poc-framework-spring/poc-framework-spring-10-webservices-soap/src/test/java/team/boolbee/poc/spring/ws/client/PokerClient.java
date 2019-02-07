package team.boolbee.poc.spring.ws.client;

import java.io.IOException;

import team.boolbee.poc.spring.ws.model.Card;
import team.boolbee.poc.spring.ws.model.PokerHandType;

public interface PokerClient {
	PokerHandType evaluateHand(Card[] cards) throws IOException;
}
