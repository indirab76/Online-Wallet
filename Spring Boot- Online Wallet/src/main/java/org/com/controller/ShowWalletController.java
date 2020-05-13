
	package org.com.controller;

	import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.com.error.RecordNotFoundException;
	import org.com.model.WalletTransaction;
import org.com.model.WalletUser;
import org.com.service.MyWalletService;
import org.com.service.WalletCreationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
	import org.springframework.web.bind.annotation.ExceptionHandler;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RestController;



	@RestController
	@RequestMapping("/wallet")
	@CrossOrigin("http://localhost:4200")
	public class ShowWalletController {

		@Autowired
		private MyWalletService creationService;
		
		private static final Logger LOGGER = LoggerFactory.getLogger(ShowWalletController.class);
		
		@RequestMapping("/showBalance/{id}")
		@ExceptionHandler(RecordNotFoundException.class)
		public double showBalance(@PathVariable("id") int uid) {
			 double balance = 0;
			try {
				balance = creationService.showBalance(uid);

			} catch (Exception e) {

				LOGGER.info(e.getMessage(), HttpStatus.NOT_FOUND);

			}
			return balance;
		}
		
		@RequestMapping("/showTransactions/{id}")
		@ExceptionHandler(RecordNotFoundException.class)
		public List<WalletTransaction> showTransactions(@PathVariable("id") int uid){
			
			List<WalletTransaction> user = new ArrayList<WalletTransaction>();
			try {
				user = creationService.showTransactions(uid);

				if (user == null)
					throw new RecordNotFoundException("Record Not Found");

			} catch (Exception e) {

				LOGGER.info(e.getMessage(), HttpStatus.NOT_FOUND);

			}
			return user;
			
		}
		@RequestMapping("/getAccountId/{id}")
		public Optional<Integer> getAccountId(@PathVariable("id") int uid){
			Optional<Integer> userId = null;
				try {
					userId = creationService.getAccountId(uid);

				} catch (Exception e) {

					LOGGER.info(e.getMessage(), HttpStatus.NOT_FOUND);

				}
				return userId;
		}
		
		@RequestMapping("/getAccountName/{id}")
		public Optional<String> getAccountName(@PathVariable("id") int uid){
			
			Optional<String> name = null;
				try {
					name = creationService.getAccountName(uid);

				} catch (Exception e) {

					LOGGER.info(e.getMessage(), HttpStatus.NOT_FOUND);

				}
				return name;
		}
		
	}

