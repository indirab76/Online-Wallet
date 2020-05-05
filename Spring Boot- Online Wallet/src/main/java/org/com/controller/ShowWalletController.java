
	package org.com.controller;

	import java.util.List;
	import org.com.error.RecordNotFoundException;
	import org.com.model.WalletTransaction;
	import org.com.service.WalletCreationService;
	import org.springframework.beans.factory.annotation.Autowired;
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
		private WalletCreationService creationService;
		
		
		@RequestMapping("/showBalance/{id}")
		@ExceptionHandler(RecordNotFoundException.class)
		public double showBalance(@PathVariable("id") int uid) {
			 
			return creationService.showBalance(uid);
		}
		
		@RequestMapping("/showTransactions/{id}")
		@ExceptionHandler(RecordNotFoundException.class)
		public List<WalletTransaction> showTransactions(@PathVariable("id") int uid){
			return creationService.showTransactions(uid);
		}
		
		
		
	}

