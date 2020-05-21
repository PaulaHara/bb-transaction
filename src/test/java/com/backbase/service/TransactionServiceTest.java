package com.backbase.service;

import com.backbase.assignment.data.OpenBank;
import com.backbase.assignment.data.OpenBank.OpenBankTransaction;
import com.backbase.assignment.data.Transaction;
import com.backbase.assignment.service.OpenBankReader;
import com.backbase.assignment.service.impl.TransactionServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

	 @Mock
	 private OpenBankReader reader;

	 @InjectMocks
	 private TransactionServiceImpl service;

	 private String type1 ="sandbox-payment";
	 private String type2 ="SANDBOX_TAN";

	 @Before
	 public void setUp() {
		  when(reader.getOpenBankData()).thenReturn(createOpenBank());
	 }

	 @Test
	 public void getTransactionsTest() {
		  List<Transaction> transactionList = service.getTransactions();

		  assertThat(transactionList).isNotNull();
		  assertThat(transactionList.size()).isEqualTo(3);
		  assertThat(transactionList.get(0).getId()).isNotNull();
		  assertThat(transactionList.get(0).getTransactionType()).isNotNull();
		  assertThat(transactionList.get(0).getTransactionAmount()).isNotNull();
		  assertThat(transactionList.get(0).getAccountId()).isNotNull();
		  assertThat(transactionList.get(0).getCounterPartyAccount()).isNotNull();
		  assertThat(transactionList.get(0).getCounterPartyLogoPath()).isNull();
		  assertThat(transactionList.get(0).getCounterPartyName()).isNotNull();
		  assertThat(transactionList.get(0).getDescription()).isNotNull();
		  assertThat(transactionList.get(0).getInstructedAmount()).isNotNull();
		  assertThat(transactionList.get(0).getTransactionCurrency()).isNotNull();
		  assertThat(transactionList.get(0).getInstructedCurrency()).isNotNull();
	 }

	 @Test
	 public void getTotalAmountForTransactionType() {
		  float total = service.getTotalAmountForTransactionType(type1);

		  assertThat(total).isEqualTo(50.7f);
	 }

	 @Test
	 public void getFilteredTransactions() {
		  List<Transaction> transactionList = service.getTransactionByType(type1);

		  assertThat(transactionList).isNotNull();
		  assertThat(transactionList.size()).isEqualTo(2);
		  assertThat(transactionList.get(0).getTransactionType()).isNotNull();
		  assertThat(transactionList.get(0).getTransactionType()).isEqualTo(type1);
	 }

	 private OpenBank createOpenBank() {
		  OpenBankTransaction openBankTransaction1 = new OpenBankTransaction("1", new OpenBank.ThisAccount("1"),
					  new OpenBank.OtherAccount(new OpenBank.Holder("Will Smith"), "1234567",
								  new OpenBank.OtherAccountMetadata(null)),
					  new OpenBank.TransactionDetails(type1, "transaction 1",
								  new OpenBank.TransactionValue("CAD", 40.5f)));

		  OpenBankTransaction openBankTransaction2 = new OpenBankTransaction("2", new OpenBank.ThisAccount("2"),
					  new OpenBank.OtherAccount(new OpenBank.Holder("Peter Park"), "8901234",
								  new OpenBank.OtherAccountMetadata(null)),
					  new OpenBank.TransactionDetails(type1, "transaction 2",
								  new OpenBank.TransactionValue("CAD", 10.2f)));

		  OpenBankTransaction openBankTransaction3 = new OpenBankTransaction("3", new OpenBank.ThisAccount("3"),
					  new OpenBank.OtherAccount(new OpenBank.Holder("Jyn Erso"), "5678901",
								  new OpenBank.OtherAccountMetadata(null)),
					  new OpenBank.TransactionDetails(type2, "transaction 3",
								  new OpenBank.TransactionValue("CAD", 30.8f)));

		  List<OpenBankTransaction> openBankTransactions = new ArrayList<>();
		  openBankTransactions.add(openBankTransaction1);
		  openBankTransactions.add(openBankTransaction2);
		  openBankTransactions.add(openBankTransaction3);

		  OpenBank openBank = new OpenBank(openBankTransactions);

		  return openBank;
	 }
}
