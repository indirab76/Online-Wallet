package org.com.controller;

import java.util.List;

import org.com.dao.WalletUserDao;
import org.com.error.TransactionException;
import org.com.error.RecordNotFoundException;
import org.com.model.WalletTransaction;
import org.com.model.WalletUser;
import org.com.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/transaction")
@CrossOrigin
public class TransactionController {

	@Autowired
	TransactionService service;

	@Autowired
	WalletUserDao dao;

	@RequestMapping("/allUser")
	public List<WalletUser> showUser() {
		return dao.findAll();
	}

	// Add Transaction
	@PostMapping("/addTransaction")
	@ExceptionHandler(TransactionException.class)
	public ResponseEntity<?> addTransaction(@RequestBody WalletTransaction wt) {
		try {
			if (service.updateBalanceAtRecieverEnd(wt) && service.updateBalanceAtSenderEnd(wt)) {
				if (service.addTransaction(wt))
					return new ResponseEntity(wt, HttpStatus.OK);
				else
					throw new TransactionException("Transaction is not added in database....");
			} else

				throw new TransactionException("Amount is not added at sender end or reciever end....");
		} catch (TransactionException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Get All Transactions
	@RequestMapping("/allTransaction")
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<?> showTransaction() {
		try {
			if (service.showAllTransactions().isEmpty())
				throw new RecordNotFoundException("There is no Transaction Record!!");
			else
				return new ResponseEntity(service.showAllTransactions(), HttpStatus.OK);
		} catch (RecordNotFoundException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	// Search By User Name
	@RequestMapping("/SearchByName/{name}/{id}")
	public ResponseEntity<?> searchByName(@PathVariable("name") String name, @PathVariable("id") int id) {
		if (name.isEmpty())
			name = "";
		return new ResponseEntity(service.SerachByname(name, id), HttpStatus.OK);
	}

	// Get Balance for User Account
	@RequestMapping("/getBalance/{id}")
	public ResponseEntity<?> getBalance(@PathVariable("id") int id) {
		return new ResponseEntity(service.getBalance(id), HttpStatus.OK);
	}

	// Get WalletUser by UserId
	@RequestMapping("/getWalletUser/{id}")
	public ResponseEntity<?> getWalletUser(@PathVariable("id") int userid) {
		return new ResponseEntity(service.getWalletUser(userid), HttpStatus.OK);
	}

}
