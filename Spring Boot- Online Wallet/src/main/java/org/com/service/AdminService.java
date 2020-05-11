package org.com.service;

import java.util.List;

import org.com.model.WalletAdmin;
import org.com.model.WalletUser;
import org.springframework.stereotype.Component;

@Component
public interface AdminService {

	public WalletAdmin addAdmin( WalletAdmin walletAdmin);
	public WalletAdmin updateAdmin( WalletAdmin walletAdmin);
	public List<WalletUser> showRegisteredUsers();
	public String removeAdminById( int aid);
	public WalletAdmin showAdminById(int uid);
	public WalletAdmin validLogin(String loginName, String password);
	public int updateStatus(String status, int user_id);
}
