package com.ejemplo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class UniversidadApplicationTests {
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Test
	void contextLoads() {
		
		String clave = encoder.encode("123456");
		String secret = encoder.encode("secret123");
		
		System.out.println(clave);
		System.out.println(secret);
	}

}
