package org.com.dao;


import java.util.List;
import java.util.Optional;

import org.com.model.WalletUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletUserDao extends JpaRepository<WalletUser, Integer>{

	
	// Returns all wallet_users whose account_status is 'registered'
	@Query( value = "select * from wallet_user where user_id in (select user_id from wallet_account where status='registered') ", nativeQuery=true)
	List<WalletUser> getRegisteredAccounts();
	
	
	//Returns all wallet_users for a given aadhaar_no
	@Query( value = "select * from wallet_user where aadhaar_no =:aadhaar_no ", nativeQuery=true)
	Optional<WalletUser> findByAadhaarNo(@Param("aadhaar_no") long aadhaar_no);
	
	
	//Returns user_id for a given login_name
	@Query( value = "select user_id from wallet_user where login_name= :login_name ", nativeQuery=true)
	Optional<Integer> validLoginName(@Param("login_name") String login_name);
	
	
	//Returns all wallet_users for given login_name and password
	@Query( value = "select * from wallet_user where login_name= :login_name and user_password= :password ", nativeQuery=true)
	Optional<WalletUser> validLogin(@Param("login_name") String login_name, @Param("password") String password);
	
	
	//Returns all wallet_users where user_name contains the substring name
	@Query("SELECT w FROM WalletUser w WHERE LOWER(w.UserName) LIKE %?1%")
	  public List<WalletUser> findByName(String name);
	
	
	//Returns user_name of the user having given account_id
	@Query( value = "select user_name from wallet_user where user_id = (select user_id from wallet_account where account_id = :account_id) ", nativeQuery=true)
	Optional<String> getAccountUserId(@Param("account_id") Integer account_id);
	
	
	//Returns the list of 'accepted' wallet_users
	@Query( value = "select * from wallet_user where user_id in (select user_id from wallet_account where status='accepted') order by user_id desc", nativeQuery=true)
	List<WalletUser> getAcceptedAccounts();
	
}
