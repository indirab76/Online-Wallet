package org.com.dao;

import javax.transaction.Transactional;

import org.com.model.CardDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CardDetailsDao  extends JpaRepository<CardDetails, Integer>

{
	
	@Query(value="select amount_available from Card_Details where card_no = ?1 ", nativeQuery=true)
	double getAccountBalanceInCard(@Param("card_no") int card_no);
	
	
	@Query(value="update card_details c set c.amount_available = ?2 where c.card_no = ?1",nativeQuery = true)
	@Modifying
	@Transactional
	int updateBalanceInCard(int card_no , double amount_available);
	

}
