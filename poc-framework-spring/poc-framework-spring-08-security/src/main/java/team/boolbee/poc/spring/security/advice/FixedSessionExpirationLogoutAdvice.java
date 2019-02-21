package team.boolbee.poc.spring.security.advice;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Clase encargada de evitar la generación de la excepción java.lang.NullPointerException
 * org.acegisecurity.ui.rememberme.TokenBasedRememberMeServices.logout(TokenBasedRememberMeServices.java:295)
 * producida al deslogarse un usuario con la sessión ya expirada.
 * El problema residía al intentar invocar un método de un objeto Authentication nulo.
 * 		public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
 * 			cancelCookie(request, response, "Logout of user " + authentication.getName());
 * 		}
 */
public class FixedSessionExpirationLogoutAdvice {

	private final Log logger = LogFactory.getLog(FixedSessionExpirationLogoutAdvice.class);

	public Object fixNullPointerException(ProceedingJoinPoint pjp) throws Throwable {
		Object[] args = pjp.getArgs();
		if (args.length != 3) {
			return pjp.proceed();
		}
		
		Authentication authentication = (Authentication) args[2];
		if (authentication == null) {
			logger.info("Authentication object is null. Instantiating object so that it does not generate an exception");
			authentication = new Authentication() {
				private static final long serialVersionUID = -6584973981127490868L;

				public String getName() {
					return null;
				}
				
				public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
				}
				
				public boolean isAuthenticated() {
					return false;
				}
				
				public Object getPrincipal() {
					return null;
				}
				
				public Object getDetails() {
					return null;
				}
				
				public Object getCredentials() {
					return null;
				}
				
				public GrantedAuthority[] getAuthorities() {
					return null;
				}
			};
		}
		
		return pjp.proceed(new Object[] { args[0], args[1], authentication });
	}
}