package org.com.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.GetMapping;
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

	private static final Logger LOGGER = LoggerFactory.getLogger(WalletCreationController.class);

	//Add User
	@PostMapping("/addUser")
	@ExceptionHandler(RecordNotFoundException.class)
	public WalletUser addUser(@RequestBody WalletUser walletUser) {
		WalletUser user = new WalletUser();

		try {
			user = creationService.addUser(walletUser);

			if (user == null)
				throw new RecordNotFoundException("Record Not Found");

		} catch (Exception e) {

			LOGGER.info(e.getMessage(), HttpStatus.NOT_FOUND);

		}
		return user;

	}

	//Remove User By Id
	@DeleteMapping("/deleteUser/{id}/{account_id}")
	@ExceptionHandler(RecordNotFoundException.class)
	public String removeUserById(@PathVariable("id") int uid, @PathVariable("account_id") int account_id) {
		String msg = null;

		try {
			msg = creationService.removeUserById(uid, account_id);

			if (!msg.equals("user removed"))
				throw new RecordNotFoundException("Record Not Found");

		} catch (Exception e) {

			LOGGER.info(e.getMessage(), HttpStatus.NOT_FOUND);

		}
		return msg;
	}

	//Update User
	@PutMapping("/updateUser")
	@ExceptionHandler(RecordNotFoundException.class)
	public WalletUser updateUser(@RequestBody WalletUser walletUser) {
		WalletUser user = new WalletUser();

		try {
			user = creationService.updateUser(walletUser);

			if (user == null)
				throw new RecordNotFoundException("Record Not Found");

		} catch (Exception e) {

			LOGGER.info(e.getMessage(), HttpStatus.NOT_FOUND);

		}
		return user;
	}

	//Show User By Id
	@RequestMapping("/showUser/{id}")
	@ExceptionHandler(RecordNotFoundException.class)
	public WalletUser showUserById(@PathVariable("id") int uid) {
		WalletUser user = new WalletUser();

		try {
			user = creationService.showUserById(uid);

			if (user == null)
				throw new RecordNotFoundException("Record Not Found");

		} catch (Exception e) {

			LOGGER.info(e.getMessage(), HttpStatus.NOT_FOUND);

		}
		return user;
	}

	//Check if entered Login Name is already present in database
	@RequestMapping("/validLoginName/{loginName}")
	@ExceptionHandler(RecordNotFoundException.class)
	public boolean validLoginName(@PathVariable("loginName") String loginName) {
		boolean valid = false;

		try {
			valid = creationService.validLoginName(loginName);

			if (valid == false)
				throw new RecordNotFoundException("Record Not Found");

		} catch (Exception e) {

			LOGGER.info(e.getMessage(), HttpStatus.NOT_FOUND);

		}
		return valid;

	}

	//Validate User Login
	@RequestMapping("/validLogin/{loginName}/{password}")
	@ExceptionHandler(RecordNotFoundException.class)
	public WalletUser validLoginName(@PathVariable("loginName") String loginName,
			@PathVariable("password") String password) {
		WalletUser user = new WalletUser();

		try {
			user = creationService.validLogin(loginName, password);

			if (user == null)
				throw new RecordNotFoundException("Record Not Found");

		} catch (Exception e) {

			LOGGER.info(e.getMessage(), HttpStatus.NOT_FOUND);

		}
		return user;
	}

	//Get All Users
	@GetMapping("/getAllUsers")
	public List<WalletUser> getAllUsers() {
		List<WalletUser> user = new ArrayList<WalletUser>();

		try {
			user = creationService.showAllUsers();

			if (user == null)
				throw new RecordNotFoundException("Record Not Found");

		} catch (Exception e) {

			LOGGER.info(e.getMessage(), HttpStatus.NOT_FOUND);

		}
		return user;
		
	}

}
