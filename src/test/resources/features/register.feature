Feature: Account Registration and Verification

Scenario: User registers and verifies account
  Given User is on the registration page
  When User inputs valid registration data
  And Clicks the register button
  Then A success alert should appear
  And User clicks OK on the alert
  And Verification page should be displayed
  When User clicks verify account button
  Then User receives verification email and clicks the link
  And User should be redirected to login page

