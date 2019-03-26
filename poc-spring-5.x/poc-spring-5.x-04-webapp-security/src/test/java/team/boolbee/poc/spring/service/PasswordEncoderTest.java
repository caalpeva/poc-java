package team.boolbee.poc.spring.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(TestContext.class)
@ContextConfiguration(locations = {"classpath:spring-security-encryption.xml"})
public class PasswordEncoderTest {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Test
	public void checkEncodedPassword() {
		String password = "bart";
		String encodedPassword = passwordEncoder.encode(password);
		System.out.println("UNENCODED: " + password);
		System.out.println("ENCODED:  " + encodedPassword);
		assertTrue(passwordEncoder.matches(password, encodedPassword));
	}
}