# Test Suite: User Flow E2E Testing - RefindsppPL Application

## Test Scope

**Application:** RefindsppPL E-commerce Web Application  
**Test Type:** End-to-End User Flow Testing  
**Pages Tested:** Home → Login → Product → Detail Product → Checkout → Profile → Review (7+ pages)

## Test Framework Applied

### 1. Equivalence Partitioning (EP)

- **Valid Data Class:** Correct email format, valid password, existing products
- **Invalid Data Class:** Incorrect email format, wrong password, non-existent data

### 2. Boundary Value Analysis (BVA)

- **Email:** Valid format vs invalid format boundaries
- **Password:** Minimum length requirements
- **Product Selection:** Available vs unavailable products

## Test Cases

| Test Case ID | Page/Feature   | Test Scenario                                    | Test Type | Input Data                                                  | Expected Result                         | Status    |
| ------------ | -------------- | ------------------------------------------------ | --------- | ----------------------------------------------------------- | --------------------------------------- | --------- |
| **TC01**     | Login          | Login with valid credentials (EP - Valid)        | Positive  | Email: "rofifaja@gmail.com", Password: "rofifaja@gmail.com" | Login successful, redirect to dashboard | ✅ Pass   |
| **TC02**     | Login          | Login with invalid email format (EP - Invalid)   | Negative  | Email: "invalid-email", Password: "validpass"               | Error message: "Invalid email format"   | ❌ Fail\* |
| **TC03**     | Login          | Login with wrong password (EP - Invalid)         | Negative  | Email: "rofifaja@gmail.com", Password: "wrongpass"          | Error message: "Invalid credentials"    | ❌ Fail\* |
| **TC04**     | Login          | Login with empty email (BVA - Boundary)          | Negative  | Email: "", Password: "validpass"                            | Error message: "Email required"         | ❌ Fail\* |
| **TC05**     | Login          | Login with empty password (BVA - Boundary)       | Negative  | Email: "rofifaja@gmail.com", Password: ""                   | Error message: "Password required"      | ❌ Fail\* |
| **TC06**     | Product        | View product list                                | Positive  | Navigate to product page                                    | Product list displayed                  | ✅ Pass   |
| **TC07**     | Product        | Select available product (EP - Valid)            | Positive  | Click on "debitis" product                                  | Navigate to detail page                 | ✅ Pass   |
| **TC08**     | Detail Product | Order available product                          | Positive  | Click "Pesan" button                                        | Redirect to WhatsApp                    | ✅ Pass   |
| **TC09**     | Checkout       | WhatsApp redirect validation                     | Positive  | After clicking Pesan                                        | URL contains "wa.me" or "whatsapp"      | ✅ Pass   |
| **TC10**     | Profile        | Navigate to profile page                         | Positive  | Profile button → Profile menu                               | Navigate to profile page                | ✅ Pass   |
| **TC11**     | Profile        | Access purchase history                          | Positive  | Click "Pembelian" tab                                       | Purchase tab activated                  | ✅ Pass   |
| **TC12**     | Profile        | Complete order process                           | Positive  | Click "Pesanan Selesai"                                     | Alert appears                           | ✅ Pass   |
| **TC13**     | Review         | Submit review with valid rating (EP - Valid)     | Positive  | Rating: 5, Review: "lorem ipsum"                            | Review submitted successfully           | ✅ Pass   |
| **TC14**     | Review         | Submit review with empty rating (BVA - Boundary) | Negative  | Rating: none, Review: "test"                                | Error: "Rating required"                | ❌ Fail\* |
| **TC15**     | Review         | Submit review with empty text (BVA - Boundary)   | Negative  | Rating: 5, Review: ""                                       | Error: "Review text required"           | ❌ Fail\* |

**Legend:**  
✅ Pass - Test case executed successfully  
❌ Fail\* - Test case failed due to application bug (documented in Bug Report)

## Test Execution Summary

- **Total Test Cases:** 15
- **Passed:** 9
- **Failed:** 6 (due to application bugs)
- **Test Coverage:** 7+ pages (Home, Login, Product, Detail, Checkout, Profile, Review)

## Boundary Value & Equivalence Classes

### Login Page

- **Valid Email Class:** Contains "@" and "." with proper format
- **Invalid Email Class:** Missing "@", missing ".", incorrect format
- **Valid Password Class:** Non-empty strings
- **Invalid Password Class:** Empty strings, null values

### Review System

- **Valid Rating Class:** Values 1-5
- **Invalid Rating Class:** No selection, values outside 1-5
- **Valid Review Text Class:** Non-empty strings (1-500 characters)
- **Invalid Review Text Class:** Empty strings, excessive length

## Test Case Management

_Note: These test cases can be imported into test case management tools like:_

- TestRail
- Zephyr
- qTest
- Azure DevOps Test Plans

## Automation Coverage

All positive test cases (TC01, TC06-TC13) are automated using:

- **Framework:** Cucumber + Selenium WebDriver
- **Pattern:** Page Object Model (POM)
- **Language:** Java
- **Test Runner:** JUnit
