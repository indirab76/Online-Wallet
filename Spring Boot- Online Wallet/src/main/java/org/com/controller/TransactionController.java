package org.com.controller;

import java.util.List;

import org.com.dao.WalletUserDao;
import org.com.error.TransactionException;
import org.com.error.RecordNotFoundException;
import org.com.model.WalletTransaction;
import org.com.model.WalletUser;
import org.com.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

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
			LOGGER.info(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			return null;
		}
	}

	@RequestMapping("/allTransaction")
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<?> showTransaction() {
		try{
			if(service.showAllTransactions().isEmpty())
				throw new RecordNotFoundException("There is no Transaction Record!!");
			else
	         	return new ResponseEntity(service.showAllTransactions(), HttpStatus.OK);
		}catch(RecordNotFoundException e) {
			LOGGER.info(e.getMessage(), HttpStatus.NOT_FOUND);
			return null;	
		}
	}
		

	@RequestMapping("/SearchByNameOrPhone/{key}/{id}")
	public ResponseEntity<?> searchByNameorPhoneNo(@PathVariable("key") String key, @PathVariable("id") int id) {
		if(key.isEmpty())
			key="";
	    return new ResponseEntity(service.SerachByNameOrPhoneNo(key, id), HttpStatus.OK);
	}

	@RequestMapping("/getBalance/{id}")
	public ResponseEntity<?> getBalance(@PathVariable("id") int id) {
		return new ResponseEntity(service.getBalance(id), HttpStatus.OK);
	}

                  @RequestMapping("/getWalletUser/{id}")
	public ResponseEntity<?> getWalletUser(@PathVariable("id") int userid) {
		return new ResponseEntity(service.getWalletUser(userid), HttpStatus.OK);
	}

}
