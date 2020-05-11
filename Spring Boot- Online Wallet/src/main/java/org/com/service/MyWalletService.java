package org.com.service;

import java.util.List;
import java.util.Optional;

import org.com.dao.WalletAccountDao;
import org.com.dao.WalletTransactionDao;
import org.com.dao.WalletUserDao;
import org.com.model.WalletAccount;
import org.com.model.WalletTransaction;
import org.com.model.WalletUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyWalletService {

	@Autowired
	WalletUserDao userDao;
	
	@Autowired
	 WalletAccountDao accountDao;
	
	@Autowired
	WalletTransactionDao transactDao;
	
	WalletUser user;
	WalletAccount account;
	
	//Show Balance By User Id
	public double showBalance(int uid) {
	
			return accountDao.getAccountBalance(uid);
	}
	
	//Show List of Transactions By User Id
	public List<WalletTransaction> showTransactions(int uid){
		
		return transactDao.getTransaction(uid);
	}
	
	//Get AccountId By UserId
	public Optional<Integer> getAccountId(int uid){
		
		return accountDao.getAccountId(uid);
	}

	//Get AccountName from UserId
	public Optional<String> getAccountName(int uid) {
		
		return accountDao.getAccountName(uid);
	}

	
}
