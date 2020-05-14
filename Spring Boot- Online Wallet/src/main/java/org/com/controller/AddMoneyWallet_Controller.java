package org.com.controller;

import java.util.List;

import org.com.dao.CardDetailsDao;
import org.com.dao.WalletAccountDao;
import org.com.dao.WalletUserDao;
import org.com.error.RecordNotFoundException;
import org.com.error.NegativeAmountException;
import org.com.model.CardDetails;
import org.com.service.CardDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallet")
@CrossOrigin
public class AddMoneyWallet_Controller {

	@Autowired
	WalletAccountDao accountDao;

	@Autowired
	WalletUserDao userDao;

	@Autowired
	CardDetailsDao cardDetailsDao;

	CardDetails cardDetails;

	@Autowired
	CardDetailsService cardservice;

	static	Logger logger  = LoggerFactory.getLogger(AddMoneyWallet_Controller.class);
	
	// Find Card By No.
	@GetMapping("/cardDetails/{card_no}")
	@ExceptionHandler(RecordNotFoundException.class)
	public CardDetails findByCardNo(@PathVariable("card_no") int card_no) {
		try {

			if (cardservice.searchCard(card_no) != null) {

				System.out.println("CardNo is found in database");
				return cardservice.searchCard(card_no);

			}

			else {

				System.out.println("2. CardNo is not found in database ");

				throw new RecordNotFoundException(" CardNo is not found in database");

			}

		}

		catch (RecordNotFoundException e) {

			System.out.println("Card record not found");

			// return new ResponseEntity(null,HttpStatus.NOT_FOUND);

			logger.info(e.getMessage(), HttpStatus.NOT_FOUND);

			return null;

		}
	}

	// Add Money
	@PutMapping("/addMoney/{user_id}/{amount}/{card_no}")
	@ExceptionHandler(NegativeAmountException.class)
	public CardDetails addMoney(@PathVariable("user_id") int user_id, @PathVariable("amount") double amount,
			@PathVariable("card_no") int card_no) {

		try

		{

			System.out.println("userid is taken from session storage");
			System.out.println("userid : " + user_id);

			if (amount > cardDetailsDao.getAccountBalanceInCard(card_no))

			{
				throw new NegativeAmountException("Balance is not added to acccount due to insufficient balance");

			}

			else

			{

				// deducting amount from Card
				double cardBalance = cardDetailsDao.getAccountBalanceInCard(card_no);

				cardBalance = cardBalance - amount;

				cardDetailsDao.updateBalanceInCard(card_no, cardBalance);

				// adding money to the wallet
				double useridbalance = accountDao.getAccountBalance(user_id);

				amount = amount + useridbalance;

				accountDao.updateBalance(user_id, amount);

				System.out.println("Balance is successfully added to account");

				return cardservice.searchCard(card_no);

			}
		}

		catch (NegativeAmountException e) {

			System.out.println(e.getMessage());

			logger.info(e.getMessage(), HttpStatus.NOT_FOUND);

			return null;
		}

	}

	// Show All Card Details
	@RequestMapping("/showAllCards")
	public List<CardDetails> show() {

		cardDetailsDao.findAll().forEach(System.out::println);
		System.out.println("\nALL card details called ");
		return cardDetailsDao.findAll();

	}

}
