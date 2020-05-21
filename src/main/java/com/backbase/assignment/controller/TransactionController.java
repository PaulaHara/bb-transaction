package com.backbase.assignment.controller;

import com.backbase.assignment.data.Transaction;
import com.backbase.assignment.service.TransactionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
	 private static final Logger logger = Logger.getLogger(TransactionController.class);

	 TransactionService transactionService;

	 @Autowired
	 public TransactionController(TransactionService transactionService) {
	 	 this.transactionService = transactionService;
	 }

	 /**
	  * <p>Retrieve a list with all transactions</p>
	  *
	  * @return list with all transactions
	  */
	 @GetMapping(headers = "Accept=application/json")
	 public List<Transaction> getTransactions() {
		  logger.info("Start GET Transactions.....");
		  List<Transaction> transactions = transactionService.getTransactions();
		  logger.info("Finished GET Transactions.");
		  return transactions;
	 }

	 /**
	  * <p>Retrieve a list of transactions filtered by type</p>
	  *
	  * @param type Transaction type
	  * @return list of transactions by type
	  */
	 @GetMapping(value = "/{type}", headers = "Accept=application/json")
	 public List<Transaction> getTransactionByType(@PathVariable String type) {
		  logger.info("Start GET Transactions by Type.....");
		  List<Transaction> transaction = transactionService.getTransactionByType(type);
		  logger.info("Finished GET Transactions by Type.");
		  return transaction;
	 }

	 /**
	  * <p>Search for all transactions of a certain type and return the sum of their transaction amount</p>
	  *
	  * @param type Transaction type
	  * @return the sum of all transactions amount by type
	  */
	 @GetMapping(value = "/total-amount/{type}", headers = "Accept=application/json")
	 public float getTotalAmountForTransactionType(@PathVariable String type) {
		  logger.info("Start GET Total Amount for Transaction Type.....");
		  float totalAmount = transactionService.getTotalAmountForTransactionType(type);
		  logger.info("Finished GET Total Amount for Transaction Type.");
		  return totalAmount;
	 }
}
