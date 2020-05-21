package com.backbase.assignment.service;

import com.backbase.assignment.data.Transaction;

import java.util.List;

public interface TransactionService {

	 List<Transaction> getTransactions();
	 List<Transaction> getTransactionByType(String type);
	 float getTotalAmountForTransactionType(String type);
}
