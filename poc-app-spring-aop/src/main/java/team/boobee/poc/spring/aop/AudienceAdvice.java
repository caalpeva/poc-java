package team.boobee.poc.spring.aop;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

import team.boobee.poc.spring.beans.Audience;

public class AudienceAdvice implements MethodBeforeAdvice, AfterReturningAdvice, ThrowsAdvice {

	private Audience audience;

	public void before(Method method, Object[] args, Object target) throws Throwable {
		audience.takeSeats();
		audience.turnOffCellPhones();
	}

	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		audience.applaud();
	}

	public void afterThrowing(Throwable throwable) {
		audience.demandRefund();
	}

	public void setAudience(Audience audience) {
		this.audience = audience;
	}
}