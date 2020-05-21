# Backend Technical Test
## Overview

This Java web application provides a RESTful API that retrieve transactions from OpenBank Sandbox and transform it to a Backabse Transaction.

The API exposes three endpoints:
* Transactions list
* Transaction filter based on transaction type
* Total amount for transaction type

## How to Build

1. Clone the project
2. Build: ```mvn clean install```
3. Run: ```mvn tomcat7:run```
4. To generate the documentation run: ```mvn javadoc:javadoc```
5. Use Postman to test it

## Test

Import the Postman collection: "BBAssignment.postman_collection.json"
* Execute **POST OAuth Token**
* Copy the access token
* Open any Transaction endpoint and replace the access token value
* It should return with the required data
* To have a new access token, you will need to execute **POST OAuth Token** again or if you already has a refresh token 
and it is not expired, you can also run **POST OAuth Token - Refresh Token**, just need to remember to replace the refresh token
on the url.
