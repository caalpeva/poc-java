package team.boobee.poc.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import team.boobee.poc.spring.beans.Audience;

public class AudienceAroundAdvice implements MethodInterceptor {

	private Audience audience;
	
	public Object invoke(MethodInvocation invocation) throws Throwable {
		try {
			audience.takeSeats();	
			audience.turnOffCellPhones();
			
			Object returnValue = invocation.proceed();
			audience.applaud();
			return returnValue;
		} catch(Throwable throwable) {
			audience.demandRefund();
			throw throwable;
		}
	}

	public void setAudience(Audience audience) {
		this.audience = audience;
	}
}