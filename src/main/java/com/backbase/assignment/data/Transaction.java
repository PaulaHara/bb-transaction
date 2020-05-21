package com.backbase.assignment.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Backbase Transaction
 */
public class Transaction {
	 private final String id;
	 private final String accountId;
	 private final String counterPartyAccount;
	 private final String counterPartyName;
	 private final String counterPartyLogoPath;
	 private final Float instructedAmount;
	 private final String instructedCurrency;
	 private final Float transactionAmount;
	 private final String transactionCurrency;
	 private final String transactionType;
	 private final String description;

	 @JsonCreator
	 public Transaction(@JsonProperty("id") String id, @JsonProperty("accountId") String accountId,
				 @JsonProperty("counterPartyAccount") String counterPartyAccount, @JsonProperty("counterPartyName") String counterPartyName,
				 @JsonProperty("counterPartyLogoPath") String counterPartyLogoPath, @JsonProperty("instructedAmount") Float instructedAmount,
				 @JsonProperty("instructedCurrency") String instructedCurrency, @JsonProperty("transactionAmount") Float transactionAmount,
				 @JsonProperty("transactionCurrency") String transactionCurrency, @JsonProperty("transactionType") String transactionType,
				 @JsonProperty("description") String description) {
		  this.id = id;
		  this.accountId = accountId;
		  this.counterPartyAccount = counterPartyAccount;
		  this.counterPartyName = counterPartyName;
		  this.counterPartyLogoPath = counterPartyLogoPath;
		  this.instructedAmount = instructedAmount;
		  this.instructedCurrency = instructedCurrency;
		  this.transactionAmount = transactionAmount;
		  this.transactionCurrency = transactionCurrency;
		  this.transactionType = transactionType;
		  this.description = description;
	 }

	 public String getId() {
		  return id;
	 }

	 public String getAccountId() {
		  return accountId;
	 }

	 public String getCounterPartyAccount() {
		  return counterPartyAccount;
	 }

	 public String getCounterPartyName() {
		  return counterPartyName;
	 }

	 public String getCounterPartyLogoPath() {
		  return counterPartyLogoPath;
	 }

	 public Float getInstructedAmount() {
		  return instructedAmount;
	 }

	 public String getInstructedCurrency() {
		  return instructedCurrency;
	 }

	 public Float getTransactionAmount() {
		  return transactionAmount;
	 }

	 public String getTransactionCurrency() {
		  return transactionCurrency;
	 }

	 public String getTransactionType() {
		  return transactionType;
	 }

	 public String getDescription() {
		  return description;
	 }
}
