package org.com.service;

import java.util.List;
import java.util.Optional;

import org.com.dao.WalletAccountDao;
import org.com.dao.WalletAdminDao;
import org.com.dao.WalletUserDao;
import org.com.model.WalletAdmin;
import org.com.model.WalletUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AdminServiceImpl")
public class AdminServiceImpl implements AdminService {

	@Autowired
	WalletUserDao userDao;
	@Autowired
	WalletAccountDao accountDao;
	@Autowired
	WalletAdminDao adminDao;

	// Create new Admin 
	public WalletAdmin addAdmin(WalletAdmin walletAdmin) {

		Optional<WalletAdmin> findById = adminDao.findById(walletAdmin.getAdminId());
		if (!findById.isPresent()) {
			WalletAdmin admin = adminDao.save(walletAdmin);

			return admin;
		}
		return null;
	}

	// Returns All Registered Users 
	public List<WalletUser> showRegisteredUsers() {
		return userDao.getRegisteredAccounts();
	}
	
	// Returns All Accepted Users Function
		public List<WalletUser> showAcceptedUsers() {
			return userDao.getAcceptedAccounts();
		}

	// Remove Admin By Id
	public String removeAdminById(int aid) {

		Optional<WalletAdmin> findById = adminDao.findById(aid);
		if (findById.isPresent()) {
			adminDao.deleteById(aid);
			return "admin removed";
		}
		return "admin not present";

	}

	// Update Admin 
	public WalletAdmin updateAdmin(WalletAdmin walletAdmin) {

		Optional<WalletAdmin> findById = adminDao.findById(walletAdmin.getAdminId());
		if (findById.isPresent()) {
			WalletAdmin admin = adminDao.save(walletAdmin);
			return admin;
		}
		return null;
	}

	// Return Admin By Id
	public WalletAdmin showAdminById(int aid) {

		Optional<WalletAdmin> findById = adminDao.findById(aid);
		if (findById.isPresent()) {
			WalletAdmin admin = findById.get();
			return admin;
		}
		return null;

	}

	// Update Account status from UserId
	@Override
	public int updateStatus(String status, int user_id) {

		Optional<WalletUser> findById = userDao.findById(user_id);
		if (findById.isPresent()) {
			return accountDao.updateStatus(user_id, status);

		}
		return 0;
	}

	// Authenticate Admin Login using loginName and password
	public WalletAdmin validLogin(String loginName, String password) {

		Optional<WalletAdmin> findById = adminDao.validLogin(loginName, password);
		if (findById.isPresent()) {
			WalletAdmin admin = findById.get();
			return admin;
		}
		return null;

	}

}
