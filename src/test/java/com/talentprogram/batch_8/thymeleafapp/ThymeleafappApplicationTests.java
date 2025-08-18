package com.talentprogram.batch_8.thymeleafapp;

import com.talentprogram.batch_8.thymeleafapp.model.Account;
import com.talentprogram.batch_8.thymeleafapp.model.Transaction;
import com.talentprogram.batch_8.thymeleafapp.model.enumType.TransactionCategory;
import com.talentprogram.batch_8.thymeleafapp.model.enumType.TransactionType;
import com.talentprogram.batch_8.thymeleafapp.service.AccountService;
import com.talentprogram.batch_8.thymeleafapp.service.TransactionService;
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

	@Autowired
	TransactionService transactionService;

	@Test
	void contextLoads() {
	}

	//@Test
	void testAccountServiceSave(){
		Account account1 = new Account();
		account1.setAccountId("09777666555");
		account1.setAddress("Yangon");
		account1.setUserName("mary");
		account1.setPassword("mary@123");
		account1.setNrcNumber("123456");
		account1.setEmail("mary1@gmail.com");
		account1.setBalance(0);

		Account account2 = new Account();
		account2.setAccountId("09444000222");
		account2.setAddress("Mandalay");
		account2.setUserName("johnsmith");
		account2.setPassword("john@123");
		account2.setNrcNumber("220066");
		account2.setEmail("john@gmail.com");
		account2.setBalance(0);

		Account account3 = new Account();
		account3.setAccountId("0975344459");
		account3.setAddress("Pyin Oo Lwin");
		account3.setUserName("sakura");
		account3.setPassword("sakura@123");
		account3.setNrcNumber("680680");
		account3.setEmail("sakura@gmail.com");
		account3.setBalance(0);

		LOGGER.info("Your Account is saved now .{} ", accountService.saveAccount(account1));
		LOGGER.info("Your Account is saved now .{} ", accountService.saveAccount(account2));
		LOGGER.info("Your Account is saved now .{} ", accountService.saveAccount(account3));

	}

	//@Test
	public void testAddInitialBalance(){
		String accountId = "09777666555";
		double initialBalance = 300000;

		boolean isAdd = accountService.addInitialBalance(accountId, initialBalance);
		if(isAdd){
			LOGGER.info("Your account's initial balance is added now.");
		}
		else {
			LOGGER.info("Please check your input!");
		}

	}

	//@Test
	void testAddTransaction(){
		Transaction transaction=new Transaction();

		/*transaction.setTransactionId(System.currentTimeMillis());
		transaction.setTransactionCategory(TransactionCategory.SNACK);
		transaction.setTransactionType(TransactionType.expense);
		transaction.setAmount(10000);
		transaction.setAccountId("09777666555");*/

		transaction.setTransactionId(System.currentTimeMillis());
		transaction.setTransactionCategory(TransactionCategory.TIP);
		transaction.setTransactionType(TransactionType.income);
		transaction.setAmount(15000);
		transaction.setAccountId("09777666555");

		transactionService.saveNewTransaction(transaction);

		Account updatedAccount = accountService.updateBalance(transaction.getAccountId(),transaction.getAmount(),transaction.getTransactionType());

		LOGGER.info(updatedAccount.toString());

	}

	//@Test
	void testGetAllExpenses(){
		String accountId= "09777666555";

		LOGGER.info("Your all expense is : {}",
					transactionService.getAllExpenseByTransactionCategory(accountId,TransactionCategory.SNACK));
	}

	//@Test
	void testGetAllIncome(){
		String accountId = "09777666555";

		LOGGER.info("Your all income is : {}",
				transactionService.getAllIncomeByTransactionCategory(accountId,TransactionCategory.TIP));
	}

	//@Test
	void testGetAllTransaction(){
		String accountId = "09777666555";

		LOGGER.info("Your all transactions are : {}",
				transactionService.getAllTransaction(accountId));
	}

	@Test
	void testGetTransactionByMonth(){
		String accountId = "09777666555";
        int month = 8;
        int year = 2025;

		LOGGER.info("Monthly transaction summary : {}",
				transactionService.getTransactionByMonth(accountId,month,year));
	}

}