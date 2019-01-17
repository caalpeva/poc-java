package team.boolbee.poc.spring.ws.service;

import team.boolbee.poc.spring.ws.model.PokerHand;
import team.boolbee.poc.spring.ws.model.PokerHandType;

public interface PokerHandEvaluator {
	PokerHandType evaluateHand(PokerHand hand);
}
