Feature: Account Registration and Verification

Scenario: User registers, verifies account, login, search, detail, checkout
# Registrasi (Assifa)
  Given User is on the registration page
  When User inputs valid registration data
  And Clicks the register button
  Then A success alert should appear
  And User clicks OK on the alert
  And Verification page should be displayed

# Verifikasi (Assifa)
  When User clicks verify account button
  Then User receives verification email and clicks the link
  And User should be redirected to login page

#Login (Syahla)
  When User enters email and password
  And User clicks the login button
  Then User should be redirected to the dashboard page

# Search Product (Syahla)
  When User searches for "rerum" on the dashboard
  And User clicks the search button
  Then Search results for "rerum" should be displayed


# See Detail (Rofi)
  And User clicks on the product "rerum"
  Then Product detail page for "rerum" should be displayed

# Checkout (Wahyu)
  When user clicks the Pesan Button
  Then user should be redirect to the WhatsApp page
  And continue to WhatsApp button is visible
  When user clicks the browser back button






