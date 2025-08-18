package com.talentprogram.batch_8.thymeleafapp;

import com.talentprogram.batch_8.thymeleafapp.model.Account;
import com.talentprogram.batch_8.thymeleafapp.service.AccountService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ThymeleafappApplicationTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(ThymeleafappApplicationTests.class);

	@Autowired
	AccountService accountService;

	@Test
	void contextLoads() {
	}

	@Test
	void testAccountServiceSave(){
		Account account1 = new Account();
		account1.setAccountId("09777666555");
		account1.setAddress("Yangon");
		account1.setUserName("mary");
		account1.setPassword("mary@123");
		account1.setNrcNumber("123456");
		account1.setEmail("mary1@gmail.com");

		Account account2 = new Account();
		account2.setAccountId("09444000222");
		account2.setAddress("Mandalay");
		account2.setUserName("johnsmith");
		account2.setPassword("john@123");
		account2.setNrcNumber("220066");
		account2.setEmail("john@gmail.com");

		Account account3 = new Account();
		account3.setAccountId("0975344459");
		account3.setAddress("Pyin Oo Lwin");
		account3.setUserName("sakura");
		account3.setPassword("sakura@123");
		account3.setNrcNumber("680680");
		account3.setEmail("sakura@gmail.com");

		LOGGER.info("Your Account is saved now .{} ", accountService.saveAccount(account1));
		LOGGER.info("Your Account is saved now .{} ", accountService.saveAccount(account2));
		LOGGER.info("Your Account is saved now .{} ", accountService.saveAccount(account3));

	}

}