package com.backbase.service;

import com.backbase.assignment.data.OpenBank;
import com.backbase.assignment.service.OpenBankReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class OpenBankReaderTest {

	 private OpenBankReader reader = new OpenBankReader();

	 @Test
	 public void getOpenBankDataTest() {
		  OpenBank openBank = reader.getOpenBankData();

		  assertThat(openBank).isNotNull();
		  assertThat(openBank.getTransactions()).isNotNull();
		  assertThat(openBank.getTransactions().size()).isEqualTo(50);
	 }
}
