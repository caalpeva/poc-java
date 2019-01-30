package team.boolbee.poc.dwr.services;

import team.boolbee.poc.dwr.model.TrafficInfo;

public interface TrafficService {
	
	TrafficInfo[] getTrafficInfo(String zipcode, int zoom, int severity);
	void setAppId(String appId);
}