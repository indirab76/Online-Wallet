package org.com;

import org.com.model.CardDetails;
import org.com.model.WalletAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;



@SpringBootTest
class ShowWalletApplicationTest {

  static	Logger logger  = LoggerFactory.getLogger(ShowWalletApplicationTest.class);
	
	RestTemplate restTemplate;
	
	@BeforeEach
	public void setUp()
	{
	
		restTemplate  = new RestTemplate();
	
	}
	
	@Test

	 public void getAccountId() {


	ResponseEntity<WalletAccount> entity2=

	restTemplate.getForEntity("http://localhost:9060/wallet/getAccountId/101",WalletAccount.class );



	Assertions.assertNotNull(entity2);

	logger.info("search for Account Id");



	 }

	 @Test

	 public void getAccountName() {


	ResponseEntity<WalletAccount> entity3=

	restTemplate.getForEntity("http://localhost:9060/wallet/getAccountName/102",WalletAccount.class );



	Assertions.assertNotNull(entity3);

	logger.info("search for Account Name");



	 }
}
