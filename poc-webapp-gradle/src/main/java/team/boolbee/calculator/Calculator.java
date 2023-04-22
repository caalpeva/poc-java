package team.boolbee.calculator;

import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
* Calculator service
*/
@Service
public class Calculator {
	int sum(int a, int b) {
		return a + b;
	}
}
