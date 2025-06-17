# Bug Report - RefindsppPL Application Testing

## Bug #001: Login Page - Missing Email Format Validation

**Bug ID:** BUG-001  
**Date:** June 15, 2025  
**Reporter:** Test Team  
**Severity:** Medium  
**Priority:** High  
**Status:** Open

**Summary:** Login form accepts invalid email format without validation

**Steps to Reproduce:**

1. Navigate to Login page (http://localhost:3000/login)
2. Enter invalid email: "invalid-email" (without @ symbol)
3. Enter valid password: "rofifaja@gmail.com"
4. Click Login button

**Expected Result:**

- Error message should display: "Please enter a valid email format"
- Login should not proceed

**Actual Result:**

- No validation error shown
- Login attempt proceeds (may result in server error or unexpected behavior)

**Environment:**

- Browser: Chrome 91+
- OS: Windows
- Application URL: http://localhost:3000

**Test Case Reference:** TC02

---

## Bug #002: Login Page - Missing Required Field Validation

**Bug ID:** BUG-002  
**Date:** June 15, 2025  
**Reporter:** Test Team  
**Severity:** Medium  
**Priority:** High  
**Status:** Open

**Summary:** Login form allows submission with empty email field

**Steps to Reproduce:**

1. Navigate to Login page
2. Leave email field empty
3. Enter password: "validpassword"
4. Click Login button

**Expected Result:**

- Error message: "Email is required"
- Form should not submit

**Actual Result:**

- No validation error
- Form submission attempted

**Test Case Reference:** TC04

---

## Bug #003: Login Page - Missing Password Validation

**Bug ID:** BUG-003  
**Date:** June 15, 2025  
**Reporter:** Test Team  
**Severity:** Medium  
**Priority:** High  
**Status:** Open

**Summary:** Login form allows submission with empty password field

**Steps to Reproduce:**

1. Navigate to Login page
2. Enter valid email: "rofifaja@gmail.com"
3. Leave password field empty
4. Click Login button

**Expected Result:**

- Error message: "Password is required"
- Form should not submit

**Actual Result:**

- No validation error
- Form submission attempted

**Test Case Reference:** TC05

---

## Bug #004: Review Modal - Missing Rating Validation

**Bug ID:** BUG-004  
**Date:** June 15, 2025  
**Reporter:** Test Team  
**Severity:** Low  
**Priority:** Medium  
**Status:** Open

**Summary:** Review form allows submission without selecting rating

**Steps to Reproduce:**

1. Complete order flow until review modal appears
2. Enter review text: "Good product"
3. Do not select any rating (leave all radio buttons unchecked)
4. Click "Kirim" button

**Expected Result:**

- Error message: "Please select a rating"
- Form should not submit

**Actual Result:**

- Review submitted without rating
- No validation error shown

**Test Case Reference:** TC14

---

## Bug #005: Review Modal - Missing Review Text Validation

**Bug ID:** BUG-005  
**Date:** June 15, 2025  
**Reporter:** Test Team  
**Severity:** Low  
**Priority:** Medium  
**Status:** Open

**Summary:** Review form allows submission with empty review text

**Steps to Reproduce:**

1. Complete order flow until review modal appears
2. Select rating: 5 stars
3. Leave review textarea empty
4. Click "Kirim" button

**Expected Result:**

- Error message: "Please enter your review"
- Form should not submit

**Actual Result:**

- Review submitted with empty text
- No validation error shown

**Test Case Reference:** TC15

---

## Bug #006: General - Inconsistent Error Handling

**Bug ID:** BUG-006  
**Date:** June 15, 2025  
**Reporter:** Test Team  
**Severity:** Medium  
**Priority:** Medium  
**Status:** Open

**Summary:** Application lacks consistent client-side validation across forms

**Description:**
Multiple forms throughout the application (Login, Review) do not implement proper client-side validation, leading to poor user experience and potential server-side errors.

**Impact:**

- Poor user experience
- Increased server load from invalid requests
- Potential security vulnerabilities

**Recommendation:**
Implement comprehensive client-side validation for all forms including:

- Required field validation
- Format validation (email, phone, etc.)
- Length validation
- Pattern matching where applicable

---

## Bug Summary Report

| Bug ID  | Component | Severity | Status | Test Case |
| ------- | --------- | -------- | ------ | --------- |
| BUG-001 | Login     | Medium   | Open   | TC02      |
| BUG-002 | Login     | Medium   | Open   | TC04      |
| BUG-003 | Login     | Medium   | Open   | TC05      |
| BUG-004 | Review    | Low      | Open   | TC14      |
| BUG-005 | Review    | Low      | Open   | TC15      |
| BUG-006 | General   | Medium   | Open   | Multiple  |

**Total Bugs Found:** 6  
**High Priority:** 2  
**Medium Priority:** 3  
**Low Priority:** 1

## Testing Notes

- All bugs were discovered during systematic boundary value and equivalence partitioning testing
- Automated tests focus on positive scenarios; negative scenarios revealed these validation issues
- Manual testing recommended for comprehensive validation coverage
