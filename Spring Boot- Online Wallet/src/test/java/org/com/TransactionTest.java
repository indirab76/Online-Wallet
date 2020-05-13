package org.com;


import org.com.model.WalletTransaction;
import org.com.model.WalletUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;



@SpringBootTest
public class TransactionTest  {
RestTemplate rst;
	
	static Logger logger = LoggerFactory.getLogger(AdminApplicationTests.class);

	@BeforeEach
	public void setup() { 
		rst = new RestTemplate();
	}
	
	
	@Test
	public void searchNameOrPhone() {
		WalletUser user = rst.getForObject("http://localhost:9090/transaction/SearchByName/{sam}/{1}", WalletUser.class);
		Assertions.assertNotNull(user);
		logger.info("search by name or phone no. works");
	}

	@Test
	public void addTransaction() {
		WalletTransaction trans = new WalletTransaction();
		trans.setAmount(100);
		trans.setReceiverId(10);
		trans.setSenderId(12);
		trans.setDateOfTransaction(LocalDateTime.MAX.now());
		ResponseEntity<?> trans2 = rst.postForEntity("http://localhost:9090/transaction/addTransaction", trans, WalletTransaction.class);
		Assertions.assertNotNull(trans2);
		logger.info("add transaction works");
		//Assertions.assetEquals(HttpStatus.OK, ResponseEntity.status(HttpStatus.OK));
	}
	
	@Test
	public void userById() {
		WalletUser user = rst.getForObject("http://localhost:9090/transaction/getWalletUser/1", WalletUser.class);
	    Assertions.assertNotNull(user);
	    logger.info("search user works by id");
	}

}
