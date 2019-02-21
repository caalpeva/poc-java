package team.boolbee.poc.spring.security.advice;

import org.acegisecurity.providers.dao.SaltSource;
import org.acegisecurity.providers.encoding.PasswordEncoder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;

import team.boolbee.poc.spring.security.model.Person;

public class PasswordEncodingAdvice {

	private Log logger = LogFactory.getLog(PasswordEncodingAdvice.class);
	
	public PasswordEncodingAdvice() { }

	public Object encodePersonPassword(ProceedingJoinPoint pjp) throws Throwable {
		Object[] args = pjp.getArgs();
		if (args.length != 1 || !(args[0] instanceof Person)) {
			return pjp.proceed();
		}

		Person person = (Person) args[0];
		String encodedPassword = passwordEncoder.encodePassword(person.getPassword().trim(),
				saltSource != null? saltSource.getSalt(null): null);

		logger.debug("SALT:  " + (saltSource != null? saltSource.getSalt(null): null));
		logger.debug("UNENCODED: " + person.getPassword());
		logger.debug("ENCODED: " + encodedPassword);

		person.setPassword(encodedPassword);
		return pjp.proceed(new Object[] { person });
	}

	public Object logValidation(ProceedingJoinPoint pjp) throws Throwable {
		//logger.debug("ProceedingJoinPoint signature: " + pjp.getSignature().toLongString());
		Object[] args = pjp.getArgs();
		if (args.length != 3) {
			return pjp.proceed();
		}
		
		logger.debug(String.format("CHECKING encodedPassword: %s, rawPassword: %s", args[0], args[1]));
		Object result = pjp.proceed();
		logger.debug("RETURNING: " + result);
		return result;
	}
	
	// injected
	private PasswordEncoder passwordEncoder;

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	private SaltSource saltSource;

	public void setSaltSource(SaltSource saltSource) {
		this.saltSource = saltSource;
	}
}