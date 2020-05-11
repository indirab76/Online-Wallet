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


@Service("walletCreationServiceImpl")
public class WalletCreationServiceImpl implements WalletCreationService{

	@Autowired
	WalletUserDao userDao;
	
	@Autowired
	 WalletAccountDao accountDao;
	
	@Autowired
	WalletTransactionDao transactDao;
	
	WalletUser user;
	WalletAccount account;
	
	//Add new User
    public WalletUser addUser( WalletUser walletUser) {
		
		Optional <WalletUser> findById = userDao.findByAadhaarNo(walletUser.getAadhaarNo());
		if(!findById.isPresent())
		{   
			account=new WalletAccount();
			account.setAccountBalance(0.0f);
			account.setStatus("registered");
			walletUser.setWalletAccount(account);
			account.setWalletUser(walletUser);
			WalletUser user=userDao.save(walletUser);
			
			return user;
		}
		return null;
	}
    
    //Remove User By Id
   public String removeUserById( int uid, int account_id) {
		
		Optional <WalletUser> findUser = userDao.findById(uid);
		Optional<WalletAccount> findAccount = accountDao.findById(account_id);
		if(findUser.isPresent() && findAccount.isPresent())
		{   accountDao.deleteById(account_id);
			userDao.deleteById(uid);
			return "user removed";
		}
		return "user not present";
		
	}
   
   //Update User By Id
   public WalletUser updateUser( WalletUser walletUser) {
		
		Optional <WalletUser> findById = userDao.findById(walletUser.getUserId());
		if(findById.isPresent())
		{
			WalletUser user = userDao.save(walletUser);
			return user;
		}
		return null;
	}
	
   //Show User By Id
	public WalletUser showUserById(int uid) {
		
		Optional <WalletUser> findById = userDao.findById(uid);
		if(findById.isPresent())
		{
			WalletUser user = findById.get();
			return user;
		}
		return null;
		
	}
	
	//Check if the entered loginname already exists in the database
     public boolean validLoginName(String loginName) {
		
		Optional<Integer> findById = userDao.validLoginName(loginName);
		if(findById.isPresent())
		{
			
			return false;
		}
		return true;
		
	}
    
     //Validate login for a user
     public WalletUser validLogin(String loginName, String password) {
 		
 		Optional<WalletUser> findById = userDao.validLogin(loginName, password);
 		if(findById.isPresent())
 		{
 			WalletUser user = findById.get();
 			return user;
 		}
 		return null;
 		
 	}

	// Get all users
	public List<WalletUser> showAllUsers(){
		return userDao.findAll();
	}
	
	//Get AccountId from User Id
	public Optional<Integer> getAccountId(int uid){
		return accountDao.getAccountId(uid);
	}

	
}
