package com.backbase.assignment.service;

import com.backbase.assignment.data.OpenBank;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

@Service
public class OpenBankReader {
	 private static final Logger logger = Logger.getLogger(OpenBankReader.class);

	 /**
	  * <p>Connect to <a href="https://apisandbox.openbankproject.com/obp/v1.2.1/banks/rbs/accounts/savings-kids-john/public/transactions">OpenBank Sandbox</a>,
	  * get the information and convert it to an OpenBank Object.
	  * </p>
	  *
	  * @return OpenBank
	  */
	 public OpenBank getOpenBankData() {
		  String url = "https://apisandbox.openbankproject.com/obp/v1.2.1/banks/rbs/accounts/savings-kids-john/public/transactions";

		  logger.info("Start reading OpenBankData....");
		  try {
				URL oracle = new URL(url);
				URLConnection yc = oracle.openConnection();
				BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

				String inputLine;
				if ((inputLine = in.readLine()) != null) {
					 Gson gson = new Gson();
					 OpenBank openBankTransaction = gson.fromJson(inputLine, OpenBank.class);

					 logger.info("Finished retrieving OpenBank data.");

					 if (logger.isDebugEnabled()) {
					 	 logger.debug("OpenBank Transactions: " + openBankTransaction);
					 }

					 return openBankTransaction;
				}
				in.close();
		  } catch (MalformedURLException e) {
				e.printStackTrace();
	 		} catch (IOException e) {
				e.printStackTrace();
		  }
		  return null;
	 }
}
