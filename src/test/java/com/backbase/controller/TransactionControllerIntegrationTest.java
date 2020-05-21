package com.backbase.controller;

import com.backbase.assignment.controller.TransactionController;
import com.backbase.assignment.data.Transaction;
import com.backbase.assignment.service.OpenBankReader;
import com.backbase.assignment.service.TransactionService;
import com.backbase.assignment.service.impl.TransactionServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class TransactionControllerIntegrationTest {

	 private OpenBankReader openBankReader;

	 private TransactionService service;

	 private TransactionController controller;

	 private String type ="SANDBOX_TAN";

	 @Before
	 public void setUp() {
	 	 openBankReader = new OpenBankReader();
	 	 service = new TransactionServiceImpl(openBankReader);
	 	 controller = new TransactionController(service);
	 }

	 @Test
	 public void getTransactionsTest() {
		  List<Transaction> transactionList = controller.getTransactions();
		  assertThat(transactionList).isNotNull();
		  assertThat(transactionList.size()).isEqualTo(50);
	 }

	 @Test
	 public void getTotalAmountForTransactionType() {
		  float total = controller.getTotalAmountForTransactionType(type);
		  assertThat(total).isEqualTo(10f);
	 }

	 @Test
	 public void getTotalAmountForTransactionNullType() {
		  float total = controller.getTotalAmountForTransactionType(null);
		  assertThat(total).isEqualTo(139.54004f);
	 }

	 @Test
	 public void getFilteredTransactions() {
		  List<Transaction> transactionList = controller.getTransactionByType(type);
		  assertThat(transactionList).isNotNull();
		  assertThat(transactionList.size()).isEqualTo(2);
	 }
}
