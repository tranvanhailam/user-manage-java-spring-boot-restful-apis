package com.kyler.user_manage;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class UserManageApplicationTests {

	@Value("${spring.application.name}")
	private String applicationName;

	@Test
	void contextLoads() {
		System.out.print(">>> run here " + applicationName);
	}
}
