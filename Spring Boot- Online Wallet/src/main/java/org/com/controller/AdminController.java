package org.com.controller;

import java.util.ArrayList;
import java.util.List;

import org.com.OnlineWalletSystemApplication;
import org.com.error.RecordNotFoundException;
import org.com.model.WalletAdmin;
import org.com.model.WalletUser;
import org.com.service.AdminService;
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

@RestController
@RequestMapping("/admin")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@CrossOrigin
public class AdminController {

	@Autowired
	private AdminService adminService;

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

	//Create Admin
	@PostMapping("/addAdmin")
	@ExceptionHandler(RecordNotFoundException.class)
	public WalletAdmin addAdmin(@RequestBody WalletAdmin walletAdmin) {
		WalletAdmin admin = new WalletAdmin();

		try {
			admin = adminService.addAdmin(walletAdmin);

			if (admin == null)
				throw new RecordNotFoundException("Record Not Found");

		} catch (Exception e) {

			LOGGER.info(e.getMessage(), HttpStatus.NOT_FOUND);

		}
		return admin;

	}

	//Delete Admin
	@DeleteMapping("/deleteAdmin/{id}")
	@ExceptionHandler(RecordNotFoundException.class)
	public String removeAdminById(@PathVariable("id") int aid) {
		String msg = null;

		try {
			msg = adminService.removeAdminById(aid);

			if (!msg.equals("admin removed"))
				throw new RecordNotFoundException("Record Not Found");

		} catch (Exception e) {

			LOGGER.info(e.getMessage(), HttpStatus.NOT_FOUND);

		}
		return msg;
	}

	//Update Admin
	@PutMapping("/updateAdmin")
	@ExceptionHandler(RecordNotFoundException.class)
	public WalletAdmin updateAdmin(@RequestBody WalletAdmin walletAdmin) {
		WalletAdmin admin = new WalletAdmin();

		try {
			admin = adminService.updateAdmin(walletAdmin);

			if (admin == null)
				throw new RecordNotFoundException("Record Not Found");

		} catch (Exception e) {

			LOGGER.info(e.getMessage(), HttpStatus.NOT_FOUND);

		}
		return admin;
	}

	//Show Admin By Id
	@RequestMapping("/showAdmin/{id}")
	@ExceptionHandler(RecordNotFoundException.class)
	public WalletAdmin showAdminById(@PathVariable("id") int aid) {
		WalletAdmin admin = new WalletAdmin();

		try {
			admin = adminService.showAdminById(aid);

			if (admin == null)
				throw new RecordNotFoundException("Record Not Found");

		} catch (Exception e) {

			LOGGER.info(e.getMessage(), HttpStatus.NOT_FOUND);

		}
		return admin;
	}

	//Returns Admin for given loginName and password
	@RequestMapping("/validLogin/{loginName}/{password}")
	@ExceptionHandler(RecordNotFoundException.class)
	public WalletAdmin validLoginName(@PathVariable("loginName") String loginName,
			@PathVariable("password") String password) {
		WalletAdmin admin = new WalletAdmin();

		try {
			admin = adminService.validLogin(loginName, password);

			if (admin == null)
				throw new RecordNotFoundException("Record Not Found");

		} catch (Exception e) {

			LOGGER.info(e.getMessage(), HttpStatus.NOT_FOUND);

		}
		return admin;
	}

	//Update User Account status for the particular user_id
	@PutMapping("/updateStatus/{id}/{status}")
	@ExceptionHandler(RecordNotFoundException.class)
	public int updateStatus(@PathVariable("id") int user_id, @PathVariable("status") String status) {

		int n = 0;

		try {
			n = adminService.updateStatus(status, user_id);

			if (n == 0)
				throw new RecordNotFoundException("Record Not Found");

		} catch (Exception e) {

			LOGGER.info(e.getMessage(), HttpStatus.NOT_FOUND);

		}
		return n;

	}

	//Show All Registered Users
	@RequestMapping("/showRegisteredUsers")
	public List<WalletUser> showRegisteredUsers() {
		
		List<WalletUser> user = new ArrayList<WalletUser>();
		try {
			user = adminService.showRegisteredUsers();

			if (user == null)
				throw new RecordNotFoundException("Record Not Found");

		} catch (Exception e) {

			LOGGER.info(e.getMessage(), HttpStatus.NOT_FOUND);

		}
		return user;
	}
	
	//Show All Accepted Users
		@RequestMapping("/showAcceptedUsers")
		public List<WalletUser> showAcceptedUsers() {
			
			List<WalletUser> user = new ArrayList<WalletUser>();
			try {
				user =  adminService.showAcceptedUsers();

				if (user == null)
					throw new RecordNotFoundException("Record Not Found");

			} catch (Exception e) {

				LOGGER.info(e.getMessage(), HttpStatus.NOT_FOUND);

			}
			return user;

		}
}
