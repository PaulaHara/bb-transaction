package com.backbase.controller;

import com.backbase.assignment.controller.TransactionController;
import com.backbase.assignment.data.Transaction;
import com.backbase.assignment.service.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransactionControllerTest {

	 @Mock
	 private TransactionService service;

	 @InjectMocks
	 private TransactionController controller;

	 private String type ="sandbox-payment";

	 @Before
	 public void setUp() {
	 	 Transaction transaction1 = new Transaction("1", "account1", "acount-1234",
					 "Gandalf", null, 50f, "CAD",
					 50f, "CAD", "sandbox-payment", "transaction 1");
	 	 Transaction transaction2 = new Transaction("2", "account2", "acount-4567",
					  "John Snow", null, 20f, "CAD",
					  20f, "CAD", "SANDBOX_TAN", "transaction 2");
	 	 Transaction transaction3 = new Transaction("3", "account1", "acount-7890",
					  "Jackie Chan", null, 75f, "CAD",
					  75f, "CAD", "sandbox-payment", "transaction 3");
	 	 List<Transaction> transactions = new ArrayList<>();
	 	 transactions.add(transaction1);
	 	 transactions.add(transaction2);
	 	 transactions.add(transaction3);

	 	 when(service.getTransactions()).thenReturn(transactions);
	 	 when(service.getTransactionByType(anyString())).thenReturn(Collections.singletonList(transaction1));
	 	 when(service.getTotalAmountForTransactionType(anyString())).thenReturn(125f);
	 }

	 @Test
	 public void getTransactionsTest() {
		  List<Transaction> transactionList = controller.getTransactions();
		  assertThat(transactionList).isNotNull();
		  assertThat(transactionList.size()).isEqualTo(3);
	 }

	 @Test
	 public void getTotalAmountForTransactionType() {
		  float total = controller.getTotalAmountForTransactionType(type);
		  assertThat(total).isEqualTo(125f);
	 }

	 @Test
	 public void getFilteredTransactions() {
		  List<Transaction> transactionList = controller.getTransactionByType(type);
		  assertThat(transactionList).isNotNull();
		  assertThat(transactionList.size()).isEqualTo(1);
	 }
}
