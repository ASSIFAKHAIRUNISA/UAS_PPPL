
# Order Flow Full Modular, email, password bisa disesuaikan
Feature: Order Flow

  Scenario: User logs in with valid credentials
    Given user is on the login page
    When user enters email "assifa@example.com" and password "Test1234!"
    Then user clicks the login button
    Then user should be logged in successfully

  Scenario: User clicks a product from the Homepage
    Given user is on the homepage
    And logo is visible
    When user clicks the first product
    Then user should see the product detail page

  Scenario: User clicks the Pesan button on the Product Detail page
    Given user is on the product detail page
    And user should see the Pesan button
    When user clicks the Pesan button
    Then user should be redirected to the WhatApp page

  Scenario: User click the Pesan button and enter the WhatsApp page
    Given user is on the WhatsApp page
    And continue to WhatsApp button is visible
    When user clicks the browser back button
    Then user should be redirected to detail product page

  Scenario: User open Transaksi page from Product Detail page
    Given user is on the product detail page
    Then user click the Foto Profile
    And user click the Profile button
    Then user should be redirected to the tansaksi page

  Scenario: User cancel the order from the Transaksi page
    Given user is on the tansaksi page
    Then user click the Pembelian tab
    And user click the Batalkan Pesanan button on the last order
    And user clicks the browser reload button
    Then user click the Pembelian tab
    Then the last order should have been canceled

  Scenario: User successfully Sign Out
    Given Foto Profile is visible
    Then user click the Foto Profile
    And user click the Sign Out button
    Then user should be redirected to the login page








