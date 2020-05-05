package org.com.controller;

import java.util.List;
import java.util.Optional;

import org.com.OnlineWalletSystemApplication;
import org.com.error.RecordNotFoundException;
import org.com.model.WalletTransaction;
import org.com.model.WalletUser;
import org.com.service.WalletCreationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.com.error.RecordNotFoundException;


@RestController
@RequestMapping("/wallet")
@CrossOrigin("http://localhost:4200")
public class WalletCreationController {

	@Autowired
	private WalletCreationService creationService;
	
	private static final Logger LOGGER=LoggerFactory.getLogger(OnlineWalletSystemApplication.class);
	
	@PostMapping("/addUser")
	@ExceptionHandler(RecordNotFoundException.class)
	public WalletUser addUser(@RequestBody WalletUser walletUser) {
		WalletUser user = new WalletUser();
		
    try {
			  user=creationService.addUser(walletUser);
			
			if(user==null)
				throw new RecordNotFoundException("Record Not Found");
			
		
		} catch(Exception e) {
			
			LOGGER.info(e.getMessage(), HttpStatus.NOT_FOUND);
			
		}
		return user;
		
	}
	
	@DeleteMapping("/deleteUser/{id}")
	@ExceptionHandler(RecordNotFoundException.class)
	public String removeUserById(@PathVariable("id") int uid) {
      String msg = null;
		
	    try {
	    	      msg = creationService.removeUserById(uid);
				
				  if(!msg.equals("user updated"))
					  throw new RecordNotFoundException("Record Not Found");
				
			} catch(Exception e) {
				
				LOGGER.info(e.getMessage(), HttpStatus.NOT_FOUND);
				
			}
			return msg;	
	}

	@PutMapping("/updateUser")
	@ExceptionHandler(RecordNotFoundException.class)
	public String updateUser(@RequestBody WalletUser walletUser) {
		String msg = null;
		
	    try {
	    	      msg = creationService.updateUser(walletUser);
				
				  if(!msg.equals("user updated"))
					  throw new RecordNotFoundException("Record Not Found");
				
			
			} catch(Exception e) {
				
				LOGGER.info(e.getMessage(), HttpStatus.NOT_FOUND);
				
			}
			return msg;	
	}
	
	@RequestMapping("/showUser/{id}")
	@ExceptionHandler(RecordNotFoundException.class)
	public WalletUser showUserById(@PathVariable("id") int uid) {
		WalletUser user = new WalletUser();
		
	    try {
				  user=creationService.showUserById(uid);
				
				if(user==null)
					throw new RecordNotFoundException("Record Not Found");
				
			
			} catch(Exception e) {
				
				LOGGER.info(e.getMessage(), HttpStatus.NOT_FOUND);
				
			}
			return user;
	}
	@RequestMapping("/validLoginName/{loginName}")
	@ExceptionHandler(RecordNotFoundException.class)
	public boolean validLoginName(@PathVariable("loginName") String loginName) {
       boolean valid = false;
		
	    try {
				  valid=creationService.validLoginName(loginName);
				
				if(valid==false)
					throw new RecordNotFoundException("Record Not Found");
				
			
			} catch(Exception e) {
				
				LOGGER.info(e.getMessage(), HttpStatus.NOT_FOUND);
				
			}
			return valid;
		
	}
	
	@RequestMapping("/validLogin/{loginName}/{password}")
	@ExceptionHandler(RecordNotFoundException.class)
	public Integer validLoginName(@PathVariable("loginName") String loginName, @PathVariable("password") String password) {
       Integer user_id = null ;
		
	    try {
				  user_id=creationService.validLogin(loginName, password);
				
				if(user_id==null)
					throw new RecordNotFoundException("Record Not Found");
				
			
			} catch(Exception e) {
				
				LOGGER.info(e.getMessage(), HttpStatus.NOT_FOUND);
				
			}
			return user_id;
	}
	
	
}
