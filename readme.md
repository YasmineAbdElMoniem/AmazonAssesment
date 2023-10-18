# Automated Testing using Selenium-Java and Rest-Assured

This repository contains automated test scripts for the specified scenarios using Selenium-Java for web automation and
Rest-Assured for API testing.

## Scenario 1: Automate Amazon Shopping Process

**Test Scenario**

1. Open Amazon Egypt and log in.
2. Open the "All" menu from the left side.
3. Click on "Video Games" and choose "All Video Games."
4. Apply filters: "Free Shipping" and "New."
5. Add all products below 15,000 EGP to the cart. If no products are found, move to the next page.
6. Ensure that all products are added to the cart.
7. Choose valu as the payment method.
8. Verify that the total amount of all items is correct
9. Delete All The product From The Cart To be More Dynamic

## Scenario 2: Test Foodics Endpoints

**Test Cases**

1. Login API request with valid credentials and Get Access Token
2. Verify that the access token is not empty.
3. Retrieve user information from the WhoAmI API using the access token.
4. Perform a logout API request using the access token.
5. Get the logout message from the API response.

**How to Run**

1. Clone this repository to your local machine.
2. Set up your Java environment with Selenium WebDriver and Rest-Assured.
3. Open the project in your preferred Java IDE.
4. Navigate to the test class for the Amazon scenario (e.g., AmazonShoppingTest.java) and run the test methods.

**Dependencies**

- Java
- Selenium WebDriver
- Rest-Assured
- TestNG (for test execution)
- Java Faker
- Allure Reports
- Maven
