package org.com.service;

import java.util.List;
import java.util.Optional;

import org.com.model.WalletTransaction;
import org.com.model.WalletUser;
import org.springframework.stereotype.Component;

@Component
public interface WalletCreationService {

	public WalletUser addUser( WalletUser walletUser);
	public String removeUserById( int uid, int account_id);
	public WalletUser updateUser( WalletUser walletUser);
	public WalletUser showUserById(int uid);
	public boolean validLoginName(String loginName);
	public WalletUser validLogin(String loginName, String password);
	public List<WalletUser> showAllUsers();
	public Optional<Integer> getAccountId(int uid);
	
	
}
