package team.boolbee.poc.spring.dwr.services;

import team.boolbee.poc.spring.dwr.model.TrafficInfo;

public interface TrafficService {
	
	TrafficInfo[] getTrafficInfo(String zipcode, int zoom, int severity);
	void setAppId(String appId);
}