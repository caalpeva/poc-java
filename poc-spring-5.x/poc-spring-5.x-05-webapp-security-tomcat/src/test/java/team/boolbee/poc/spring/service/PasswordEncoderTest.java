package team.boolbee.poc.spring.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.util.DigestUtils;

public class PasswordEncoderTest {
	
	@Test
	public void checkEncodedPassword() {
		String password = "raziel";
		String encodedPassword = DigestUtils.md5DigestAsHex(password.getBytes());
		System.out.println("UNENCODED: " + password);
		System.out.println("ENCODED:  " + encodedPassword);
		assertTrue("6390AB7B006D48A5491A950B768D55FE".compareToIgnoreCase(encodedPassword) == 0);
	}
}