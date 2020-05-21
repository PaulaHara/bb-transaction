package com.backbase.assignment.service.impl;

import com.backbase.assignment.data.OpenBank;
import com.backbase.assignment.data.Transaction;
import com.backbase.assignment.service.OpenBankReader;
import com.backbase.assignment.service.TransactionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {
	 private static final Logger logger = Logger.getLogger(TransactionServiceImpl.class);

	 private OpenBankReader openBankReader;

	 @Autowired
	 public TransactionServiceImpl(OpenBankReader openBankReader) {
	 	 this.openBankReader = openBankReader;
	 }

	 public List<Transaction> getTransactions() {
		  OpenBank openBank = openBankReader.getOpenBankData();
	 	 return convertOpenBankToBBTransaction(openBank);
	 }

	 public List<Transaction> getTransactionByType(String type) {
		  List<Transaction> transactions = filterTransactionsByType(getTransactions(), type);
		  return transactions;
	 }

	 public float getTotalAmountForTransactionType(String type) {
		  List<Transaction> transactions = filterTransactionsByType(getTransactions(), type);

		  float totalAmount = 0;
		  for (Transaction transaction : transactions) {
				totalAmount += transaction.getTransactionAmount();
		  }

		  return totalAmount;
	 }

	 /**
	  * <p>Receive an OpenBank Object and transform it into a Backbase Transaction Object</p>
	  *
	  * @param openBank
	  * @return List of Transactions
	  */
	 private List<Transaction> convertOpenBankToBBTransaction(OpenBank openBank) {
	 	 logger.info("Start conversion of OpenBankTransaction to BBTransaction....");
	 	 List<Transaction> transactions = new ArrayList<>();

	 	 for (OpenBank.OpenBankTransaction opTransaction : openBank.getTransactions()) {
	 	 	 Transaction transaction = new Transaction(opTransaction.getId(), opTransaction.getThisAccount().getId(),
						 opTransaction.getOtherAccount().getNumber(), opTransaction.getOtherAccount().getHolder().getName(),
						 opTransaction.getOtherAccount().getMetadata().getImageUrl(), opTransaction.getTransactionDetails().getValue().getAmount(),
						 opTransaction.getTransactionDetails().getValue().getCurrency(), opTransaction.getTransactionDetails().getValue().getAmount(),
						 opTransaction.getTransactionDetails().getValue().getCurrency(), opTransaction.getTransactionDetails().getType(),
						 opTransaction.getTransactionDetails().getDescription());
			  transactions.add(transaction);
		 }

	 	 logger.info("Finished conversion of OpenBankTransaction to BBTransaction.");

	 	 if (logger.isDebugEnabled()) {
	 	 	 logger.debug("List of Transactions: " + transactions);
		 }

	 	 return transactions;
	 }

	 /**
	  * <p>Filter a list of Transactions by type</p>
	  * <p>It also filters by type = null</p>
	  *
	  * @param transactions
	  * @param type
	  * @return List of Transactions
	  */
	 private List<Transaction> filterTransactionsByType(List<Transaction> transactions, String type) {
		  logger.info("Start filtering Transactions by type....");
		  List<Transaction> transactionsFiltered;

		  if (type == null) {
				transactionsFiltered = transactions.stream()
							.filter(transaction -> Objects.nonNull(transaction))
							.filter(transaction -> Objects.isNull(transaction.getTransactionType()))
							.collect(Collectors.toList());

				logger.info("Finished filtering Transactions by type null.");

				return transactionsFiltered;
		  }

		  transactionsFiltered = transactions.stream()
					  .filter(transaction -> Objects.nonNull(transaction))
					  .filter(transaction -> Objects.nonNull(transaction.getTransactionType()))
					  .filter(transaction -> transaction.getTransactionType().equals(type))
					  .collect(Collectors.toList());

		  logger.info("Finished filtering Transactions by type = " + type + ".");

		  return transactionsFiltered;
	 }
}
