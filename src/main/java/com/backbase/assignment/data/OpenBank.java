package com.backbase.assignment.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OpenBank {
	 private final List<OpenBankTransaction> transactions;

	 @JsonCreator
	 public OpenBank(@JsonProperty("transactions") List<OpenBankTransaction> transactions) {
		  this.transactions = transactions;
	 }

	 public List<OpenBankTransaction> getTransactions() {
		  return transactions;
	 }

	 public static class OpenBankTransaction {
		  private String id;
		  private ThisAccount this_account;
		  private OtherAccount other_account;
		  private TransactionDetails details;

		  @JsonCreator
		  public OpenBankTransaction(@JsonProperty("id") String id, @JsonProperty("this_account") ThisAccount this_account,
					  @JsonProperty("other_account") OtherAccount other_account,
					  @JsonProperty("details") TransactionDetails details) {
				this.id = id;
				this.this_account = this_account;
				this.other_account = other_account;
				this.details = details;
		  }

		  public String getId() {
				return id;
		  }

		  public ThisAccount getThisAccount() {
				return this_account;
		  }

		  public OtherAccount getOtherAccount() {
				return other_account;
		  }

		  public TransactionDetails getTransactionDetails() {
				return details;
		  }
	 }

	 public static class ThisAccount {
		  private final String id;

		  @JsonCreator
		  public ThisAccount(@JsonProperty("id") String id) {
				this.id = id;
		  }

		  public String getId() {
				return id;
		  }
	 }

	 public static class OtherAccount {
		  private final Holder holder;
		  private final String number;
		  private final OtherAccountMetadata metadata;

		  @JsonCreator
		  public OtherAccount(@JsonProperty("holders") Holder holder,
					  @JsonProperty("number") String number, @JsonProperty("metadata") OtherAccountMetadata metadata) {
				this.holder = holder;
				this.number = number;
				this.metadata = metadata;
		  }

		  public Holder getHolder() {
				return holder;
		  }

		  public String getNumber() {
				return number;
		  }

		  public OtherAccountMetadata getMetadata() {
				return metadata;
		  }
	 }

	 public static class Holder {
		  private final String name;

		  @JsonCreator
		  public Holder(@JsonProperty("name") String name) {
				this.name = name;
		  }

		  public String getName() {
				return name;
		  }
	 }

	 public static class OtherAccountMetadata {
		  private final String imageUrl;

		  @JsonCreator
		  public OtherAccountMetadata(@JsonProperty("imageUrl") String imageUrl) {
				this.imageUrl = imageUrl;
		  }

		  public String getImageUrl() {
				return imageUrl;
		  }
	 }

	 public static class TransactionDetails {
		  private final String type;
		  private final String description;
		  private final TransactionValue value;

		  @JsonCreator
		  public TransactionDetails(@JsonProperty("type") String type, @JsonProperty("description") String description,
					  @JsonProperty("value") TransactionValue value) {
				this.type = type;
				this.description = description;
				this.value = value;
		  }

		  public String getType() {
				return type;
		  }

		  public String getDescription() {
				return description;
		  }

		  public TransactionValue getValue() {
				return value;
		  }
	 }

	 public static class TransactionValue {
		  private final String currency;
		  private final Float amount;

		  @JsonCreator
		  public TransactionValue(@JsonProperty("currency") String currency,
					  @JsonProperty("amount") Float amount) {
				this.currency = currency;
				this.amount = amount;
		  }

		  public String getCurrency() {
				return currency;
		  }

		  public Float getAmount() {
				return amount;
		  }
	 }
}
