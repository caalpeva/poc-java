package team.boolbee.poc.spring.webservices.service;

import team.boolbee.poc.spring.webservices.model.PokerHand;
import team.boolbee.poc.spring.webservices.model.PokerHandType;

public interface PokerHandEvaluator {
	PokerHandType evaluateHand(PokerHand hand);
}
