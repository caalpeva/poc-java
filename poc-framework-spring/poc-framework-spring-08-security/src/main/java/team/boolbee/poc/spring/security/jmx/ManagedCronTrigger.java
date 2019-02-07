package team.boolbee.poc.spring.security.jmx;

import java.util.Date;

public interface ManagedCronTrigger {
	void setCronExpression(String cronExpression);
	String getCronExpression();
	void setNextFireTime(Date date);
	Date getNextFireTime();
}