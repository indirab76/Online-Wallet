package org.com.dao;

import java.util.Optional;

import org.com.model.WalletAdmin;
import org.com.model.WalletUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletAdminDao extends JpaRepository<WalletAdmin, Integer> {
	
	
    //Returns wallet_admin for given login_name and password
	@Query( value = "select * from wallet_admin where login_name= :login_name and admin_password= :password ", nativeQuery=true)
	Optional<WalletAdmin> validLogin(@Param("login_name") String login_name, @Param("password") String password);
	
	
}
