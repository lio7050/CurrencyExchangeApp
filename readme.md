Currency Exchange and Discount Calculation

Overview

This is a Spring Boot application that integrates with a third-party currency exchange API to retrieve real-time exchange rates. It calculates the total payable amount for a bill in a specified currency after applying applicable discounts.

Build

./gradlew clean build

Run

./gradlew bootRun


Application with start on your tomcat port
http://localhost:8080/api/calculate

sample request object to test on postman (POST call)

{
"totalAmount": 2000,
"employee": true,
"affiliate": true,
"customerTenure": 2,
"grocery": true,
"originalCurrency": "USD"
}