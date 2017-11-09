package team.boobee.poc.spring.beans;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AnnotatedAudience {

	public AnnotatedAudience() {}
	
	@Pointcut("execution(* *.perform(..))")
	public void performance() {}
	
	@Before("performance()")
	public void takeSeats() {
		System.out.println("The audience is taking their seats");
	}
	
	@Before("performance()")
	public void turnOffCellPhones() {
		System.out.println("The audience is turning off their cellphones");
	}
	
	@AfterReturning("performance()")
	public void applaud() {
		System.out.println("CLAP CLAP CLAP CLAP CLAP (the audience applauds)");
	}
	
	@AfterThrowing("performance()")
	public void demandRefund() {
		System.out.println("Boo! We want our money back!");
	}
	
//	@Around("performance()")
//	public void watchPerformance(ProceedingJoinPoint joinPoint) {
//		takeSeats();
//		turnOffCellPhones();
//		try {
//			joinPoint.proceed();
//			applaud();
//		} catch (Throwable e) {
//			demandRefund();
//		}
//	}
}