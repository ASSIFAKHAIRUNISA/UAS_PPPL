package com.refindspppl.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Page Object untuk interaksi profil user setelah kembali dari WhatsApp.
 * Digunakan untuk klik tombol profil (foto profil) dan menu Profile.
 */
public class ProfilePage {
    private WebDriver driver;
    private WebDriverWait wait;
    // Selector tombol profil (foto profil)
    private By profileButton = By.xpath("//button[./img[@alt='Foto Profil']]");
    // Selector menu Profile di dropdown
    private By profileMenu = By.xpath("//a[@href='/user_transaksi_penjualan' and .//p[text()='Profile']]");

    public ProfilePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    /**
     * Klik tombol profil (foto profil)
     */
    public void clickProfileButton() {
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(profileButton));
        driver.findElement(profileButton).click();
    }

    /**
     * Klik menu Profile di dropdown
     */
    public void clickProfileMenu() {
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(profileMenu));
        driver.findElement(profileMenu).click();
    }
    
    /**
     * Klik tab Pembelian di halaman profil
     */
    public void clickTabPembelian() {
        // Selector untuk tab Pembelian berdasarkan elemen yang diberikan
        By pembelianTab = By.xpath("//li[@role='tab'][contains(@class, 'react-tabs__tab')][text()='Pembelian']");
        
        // Alternatif selector jika yang pertama tidak bekerja
        By pembelianTabAlt = By.xpath("//li[contains(@class, 'react-tabs__tab') and text()='Pembelian']");
        
        try {
            wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(pembelianTab));
            driver.findElement(pembelianTab).click();
            System.out.println("Berhasil klik tab Pembelian");
        } catch (Exception e) {
            System.out.println("Mencoba selector alternatif untuk tab Pembelian...");
            try {
                wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(pembelianTabAlt));
                driver.findElement(pembelianTabAlt).click();
                System.out.println("Berhasil klik tab Pembelian dengan selector alternatif");
            } catch (Exception e2) {
                System.out.println("Gagal menemukan tab Pembelian. Page source:");
                System.out.println(driver.getPageSource());
                throw new RuntimeException("Tidak dapat menemukan tab Pembelian", e2);
            }
        }
    }
    
    /**
     * Verifikasi bahwa tab Pembelian aktif
     */
    public boolean isTabPembelianActive() {
        try {
            // Cek apakah tab Pembelian memiliki class "react-tabs__tab--selected"
            By activeTab = By.xpath("//li[contains(@class, 'react-tabs__tab--selected') and text()='Pembelian']");
            wait.until(org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated(activeTab));
            return driver.findElement(activeTab).isDisplayed();
        } catch (Exception e) {
            System.out.println("Tab Pembelian tidak aktif atau tidak ditemukan");
            return false;
        }
    }
    
    /**
     * Klik tombol Pesanan Selesai di halaman profil
     */
    public void clickPesananSelesai() {
        // Selector untuk tombol Pesanan Selesai berdasarkan elemen yang diberikan
        By pesananSelesaiButton = By.xpath("//button[contains(@class, 'bg-[#0D96C4]') and text()='Pesanan Selesai']");
        
        // Alternatif selector jika yang pertama tidak bekerja
        By pesananSelesaiButtonAlt = By.xpath("//button[text()='Pesanan Selesai']");
        
        try {
            wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(pesananSelesaiButton));
            driver.findElement(pesananSelesaiButton).click();
            System.out.println("Berhasil klik tombol Pesanan Selesai");
        } catch (Exception e) {
            System.out.println("Mencoba selector alternatif untuk tombol Pesanan Selesai...");
            try {
                wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(pesananSelesaiButtonAlt));
                driver.findElement(pesananSelesaiButtonAlt).click();
                System.out.println("Berhasil klik tombol Pesanan Selesai dengan selector alternatif");
            } catch (Exception e2) {
                System.out.println("Gagal menemukan tombol Pesanan Selesai. Page source:");
                System.out.println(driver.getPageSource());
                throw new RuntimeException("Tidak dapat menemukan tombol Pesanan Selesai", e2);
            }
        }
    }
    
    /**
     * Handle alert yang muncul setelah klik Pesanan Selesai dan klik OK
     */
    public void handleAlert() {
        try {
            // Tunggu alert muncul
            wait.until(org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent());
            
            // Switch ke alert dan klik OK
            org.openqa.selenium.Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            System.out.println("Alert text: " + alertText);
            
            alert.accept(); // Klik OK
            System.out.println("Berhasil klik OK pada alert");
        } catch (Exception e) {
            System.out.println("Gagal handle alert: " + e.getMessage());
            throw new RuntimeException("Tidak dapat handle alert", e);
        }
    }
    
    /**
     * Handle alert konfirmasi yang muncul setelah mengirim ulasan
     */
    public void handleConfirmationAlert() {
        try {
            System.out.println("Menunggu alert konfirmasi setelah mengirim ulasan...");
            
            // Tunggu alert muncul dengan timeout yang lebih lama
            wait.until(org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent());
            
            // Switch ke alert dan ambil teksnya
            org.openqa.selenium.Alert confirmationAlert = driver.switchTo().alert();
            String alertText = confirmationAlert.getText();
            System.out.println("Alert konfirmasi text: " + alertText);
            
            // Klik OK pada alert konfirmasi
            confirmationAlert.accept();
            System.out.println("✓ Berhasil klik OK pada alert konfirmasi");
            
            // Tunggu sebentar setelah alert ditutup
            Thread.sleep(1000);
            
        } catch (Exception e) {
            System.out.println("Gagal handle alert konfirmasi: " + e.getMessage());
            
            // Debug: Cek apakah masih ada alert
            try {
                if (driver.switchTo().alert() != null) {
                    System.out.println("Alert masih ada setelah error");
                }
            } catch (Exception debugE) {
                System.out.println("Tidak ada alert tersisa");
            }
            
            throw new RuntimeException("Tidak dapat handle alert konfirmasi", e);
        }
    }
    
    /**
     * Verifikasi apakah popup/modal rating sedang tampil
     */
    public boolean isRatingPopupVisible() {
        try {
            // Coba beberapa selector untuk popup/modal
            By[] popupSelectors = {
                By.xpath("//div[contains(@class, 'modal')]"),
                By.xpath("//div[contains(@class, 'popup')]"),
                By.xpath("//div[.//input[@name='rating']]"),
                By.xpath("//form[.//input[@name='rating']]"),
                By.xpath("//*[contains(@class, 'modal') or contains(@class, 'popup')]"),
                By.xpath("//div[.//textarea[@name='komentar']]")
            };
            
            for (By selector : popupSelectors) {
                try {
                    org.openqa.selenium.WebElement popup = driver.findElement(selector);
                    if (popup.isDisplayed()) {
                        System.out.println("Popup ditemukan dengan selector: " + selector);
                        return true;
                    }
                } catch (Exception e) {
                    // Continue to next selector
                }
            }
            
            System.out.println("Popup tidak ditemukan atau tidak visible");
            return false;
        } catch (Exception e) {
            System.out.println("Error checking popup visibility: " + e.getMessage());
            return false;
        }
    }    /**
     * Pilih rating 5 pada popup penilaian
     */
    public void selectRating5() {
        try {
            // Tunggu popup/modal muncul terlebih dahulu
            System.out.println("Menunggu modal penilaian muncul...");
            
            // Tunggu modal dengan class "modal" muncul
            By modalContainer = By.xpath("//div[contains(@class, 'modal')]");
            wait.until(org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated(modalContainer));
            wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(modalContainer));
            
            System.out.println("Modal ditemukan, mencari radio button rating 5...");
            
            // Tunggu tambahan untuk memastikan modal fully loaded
            Thread.sleep(2000);
            
            // XPath yang lebih spesifik - radio button di dalam modal
            By[] rating5Selectors = {
                // Radio button value="5" di dalam div dengan class modal
                By.xpath("//div[contains(@class, 'modal')]//input[@type='radio' and @value='5' and @name='rating']"),
                // Alternative: cari di dalam modal apapun
                By.xpath("//div[contains(@class, 'modal')]//input[@type='radio' and @value='5']"),
                // Alternative: cari radio button rating 5 di mana pun dalam modal
                By.xpath("//div[contains(@class, 'modal')]//input[@value='5']"),
                // Backup: tanpa modal constraint
                By.xpath("//input[@type='radio' and @value='5' and @name='rating']")
            };
              org.openqa.selenium.WebElement radioElement = null;
            
            // Coba setiap selector sampai ketemu
            for (By selector : rating5Selectors) {
                try {
                    System.out.println("Mencoba selector: " + selector);
                    wait.until(org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated(selector));
                    radioElement = driver.findElement(selector);
                    
                    if (radioElement != null && radioElement.isDisplayed()) {
                        System.out.println("Radio button ditemukan dengan selector: " + selector);
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Selector gagal: " + selector + " - " + e.getMessage());
                    continue;
                }
            }
            
            if (radioElement == null) {
                throw new RuntimeException("Radio button rating 5 tidak ditemukan dengan semua selector");
            }
            
            // Scroll ke elemen
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", radioElement);
            Thread.sleep(1000);
            
            // Coba klik dengan berbagai cara
            try {
                // Cara 1: Klik langsung
                radioElement.click();
                System.out.println("Berhasil klik radio button dengan click() biasa");
            } catch (Exception e1) {
                try {
                    // Cara 2: JavaScript click
                    ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", radioElement);
                    System.out.println("Berhasil klik radio button dengan JavaScript click");
                } catch (Exception e2) {
                    try {
                        // Cara 3: Force click dengan JavaScript
                        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].checked = true; arguments[0].dispatchEvent(new Event('change'));", radioElement);
                        System.out.println("Berhasil set radio button dengan JavaScript force");
                    } catch (Exception e3) {
                        throw new RuntimeException("Gagal klik radio button dengan semua metode", e3);
                    }
                }
            }
            
            // Verifikasi radio button terpilih
            Thread.sleep(500);
            boolean isSelected = radioElement.isSelected() || 
                               "true".equals(radioElement.getAttribute("checked")) ||
                               radioElement.getAttribute("aria-checked") != null;
            
            if (isSelected) {
                System.out.println("✓ Radio button rating 5 berhasil dipilih");
            } else {
                System.out.println("⚠ Warning: Radio button mungkin belum terpilih");
            }
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Thread interrupted: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Gagal memilih rating 5: " + e.getMessage());
            
            // Debug info
            try {
                System.out.println("=== DEBUG INFO ===");
                System.out.println("Current URL: " + driver.getCurrentUrl());
                
                // Cek apakah modal ada
                java.util.List<org.openqa.selenium.WebElement> modals = driver.findElements(By.xpath("//div[contains(@class, 'modal')]"));
                System.out.println("Found " + modals.size() + " modal elements");
                
                // List semua input dalam modal
                java.util.List<org.openqa.selenium.WebElement> modalInputs = driver.findElements(By.xpath("//div[contains(@class, 'modal')]//input"));
                System.out.println("Found " + modalInputs.size() + " input elements in modal:");
                for (int i = 0; i < modalInputs.size(); i++) {
                    org.openqa.selenium.WebElement input = modalInputs.get(i);
                    System.out.println("  " + (i+1) + ". Type: " + input.getAttribute("type") + 
                                     ", Name: " + input.getAttribute("name") + 
                                     ", Value: " + input.getAttribute("value") +
                                     ", Visible: " + input.isDisplayed());
                }
            } catch (Exception debugE) {
                System.out.println("Debug error: " + debugE.getMessage());
            }
            
            throw new RuntimeException("Tidak dapat memilih rating 5", e);
        }
    }    /**
     * Mengisi ulasan pada textarea
     */
    public void fillReview(String reviewText) {
        try {
            System.out.println("Mencari textarea untuk ulasan dalam modal...");
            
            // XPath yang lebih spesifik - textarea di dalam modal
            By[] textareaSelectors = {
                // Textarea dengan id dan name di dalam modal
                By.xpath("//div[contains(@class, 'modal')]//textarea[@id='review' and @name='komentar']"),
                // Alternative: textarea dengan name komentar di dalam modal
                By.xpath("//div[contains(@class, 'modal')]//textarea[@name='komentar']"),
                // Alternative: textarea dengan id review di dalam modal
                By.xpath("//div[contains(@class, 'modal')]//textarea[@id='review']"),
                // Alternative: any textarea in modal
                By.xpath("//div[contains(@class, 'modal')]//textarea"),
                // Backup: tanpa modal constraint
                By.xpath("//textarea[@id='review' and @name='komentar']")
            };
            
            org.openqa.selenium.WebElement textareaElement = null;
            
            // Coba setiap selector sampai ketemu
            for (By selector : textareaSelectors) {
                try {
                    System.out.println("Mencoba textarea selector: " + selector);
                    wait.until(org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated(selector));
                    textareaElement = driver.findElement(selector);
                    
                    if (textareaElement != null && textareaElement.isDisplayed()) {
                        System.out.println("Textarea ditemukan dengan selector: " + selector);
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Textarea selector gagal: " + selector + " - " + e.getMessage());
                    continue;
                }
            }
            
            if (textareaElement == null) {
                throw new RuntimeException("Textarea tidak ditemukan dengan semua selector");
            }
            
            // Scroll ke textarea
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", textareaElement);
            Thread.sleep(1000);
            
            // Pastikan textarea clickable
            wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(textareaElement));
            
            // Fokus pada textarea dengan berbagai cara
            try {
                // Cara 1: Klik untuk fokus
                textareaElement.click();
                System.out.println("Textarea diklik untuk fokus");
            } catch (Exception e) {
                // Cara 2: JavaScript focus
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].focus();", textareaElement);
                System.out.println("Textarea difokus dengan JavaScript");
            }
            
            Thread.sleep(500);
            
            // Clear dan isi textarea dengan berbagai cara
            try {
                // Cara 1: Clear dan sendKeys biasa
                textareaElement.clear();
                textareaElement.sendKeys(reviewText);
                System.out.println("Textarea diisi dengan cara biasa");
            } catch (Exception e1) {
                try {
                    // Cara 2: JavaScript setValue
                    ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1];", textareaElement, reviewText);
                    System.out.println("Textarea diisi dengan JavaScript setValue");
                } catch (Exception e2) {
                    try {
                        // Cara 3: JavaScript innerHTML
                        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].innerHTML = arguments[1];", textareaElement, reviewText);
                        System.out.println("Textarea diisi dengan JavaScript innerHTML");
                    } catch (Exception e3) {
                        throw new RuntimeException("Gagal mengisi textarea dengan semua metode", e3);
                    }
                }
            }
            
            // Trigger change event
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].dispatchEvent(new Event('input')); arguments[0].dispatchEvent(new Event('change'));", textareaElement);
            
            // Verifikasi teks berhasil diisi
            Thread.sleep(500);
            String actualText = textareaElement.getAttribute("value");
            if (actualText == null || actualText.isEmpty()) {
                actualText = textareaElement.getText();
            }
            
            if (reviewText.equals(actualText)) {
                System.out.println("✓ Berhasil mengisi ulasan: " + reviewText);
            } else {
                System.out.println("⚠ Warning: Teks yang diisi tidak sesuai. Expected: '" + reviewText + "', Actual: '" + actualText + "'");
                // Coba sekali lagi dengan force
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'));", textareaElement, reviewText);
            }
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Thread interrupted: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Gagal mengisi ulasan: " + e.getMessage());
            
            // Debug info
            try {
                System.out.println("=== TEXTAREA DEBUG INFO ===");
                
                // List semua textarea dalam modal
                java.util.List<org.openqa.selenium.WebElement> modalTextareas = driver.findElements(By.xpath("//div[contains(@class, 'modal')]//textarea"));
                System.out.println("Found " + modalTextareas.size() + " textarea elements in modal:");
                for (int i = 0; i < modalTextareas.size(); i++) {
                    org.openqa.selenium.WebElement textarea = modalTextareas.get(i);
                    System.out.println("  " + (i+1) + ". ID: " + textarea.getAttribute("id") + 
                                     ", Name: " + textarea.getAttribute("name") + 
                                     ", Placeholder: " + textarea.getAttribute("placeholder") +
                                     ", Visible: " + textarea.isDisplayed());
                }
            } catch (Exception debugE) {
                System.out.println("Textarea debug error: " + debugE.getMessage());
            }
            
            throw new RuntimeException("Tidak dapat mengisi ulasan", e);
        }
    }
      /**
     * Klik tombol Kirim pada popup penilaian
     */
    public void clickKirimReview() {
        try {
            System.out.println("Mencari tombol Kirim dalam modal...");
            
            // XPath yang lebih spesifik - tombol Kirim di dalam modal
            By[] kirimSelectors = {
                // Tombol submit dalam modal dengan class bg-blue_btn
                By.xpath("//div[contains(@class, 'modal')]//button[@type='submit' and contains(@class, 'bg-blue_btn')]"),
                // Alternative: button dengan text Kirim dalam modal
                By.xpath("//div[contains(@class, 'modal')]//button[text()='Kirim']"),
                // Alternative: submit button dalam modal
                By.xpath("//div[contains(@class, 'modal')]//button[@type='submit']"),
                // Alternative: button dengan bg-blue dalam modal
                By.xpath("//div[contains(@class, 'modal')]//button[contains(@class, 'bg-blue')]"),
                // Backup: tanpa modal constraint
                By.xpath("//button[@type='submit' and contains(@class, 'bg-blue_btn')]")
            };
            
            org.openqa.selenium.WebElement kirimButton = null;
            
            // Coba setiap selector sampai ketemu
            for (By selector : kirimSelectors) {
                try {
                    System.out.println("Mencoba tombol Kirim selector: " + selector);
                    wait.until(org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated(selector));
                    kirimButton = driver.findElement(selector);
                    
                    if (kirimButton != null && kirimButton.isDisplayed()) {
                        System.out.println("Tombol Kirim ditemukan dengan selector: " + selector);
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Tombol Kirim selector gagal: " + selector + " - " + e.getMessage());
                    continue;
                }
            }
            
            if (kirimButton == null) {
                throw new RuntimeException("Tombol Kirim tidak ditemukan dengan semua selector");
            }
            
            // Scroll ke tombol
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", kirimButton);
            Thread.sleep(1000);
            
            // Pastikan tombol clickable
            wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(kirimButton));
            
            // Klik tombol dengan berbagai cara
            try {
                // Cara 1: Klik langsung
                kirimButton.click();
                System.out.println("✓ Berhasil klik tombol Kirim dengan click() biasa");
            } catch (Exception e1) {
                try {
                    // Cara 2: JavaScript click
                    ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", kirimButton);
                    System.out.println("✓ Berhasil klik tombol Kirim dengan JavaScript click");
                } catch (Exception e2) {
                    try {
                        // Cara 3: Submit form jika tombol adalah submit
                        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].form.submit();", kirimButton);
                        System.out.println("✓ Berhasil submit form dengan JavaScript");
                    } catch (Exception e3) {
                        throw new RuntimeException("Gagal klik tombol Kirim dengan semua metode", e3);
                    }
                }
            }
            
        } catch (Exception e) {
            System.out.println("Gagal klik tombol Kirim: " + e.getMessage());
            
            // Debug info
            try {
                System.out.println("=== TOMBOL KIRIM DEBUG INFO ===");
                
                // List semua button dalam modal
                java.util.List<org.openqa.selenium.WebElement> modalButtons = driver.findElements(By.xpath("//div[contains(@class, 'modal')]//button"));
                System.out.println("Found " + modalButtons.size() + " button elements in modal:");
                for (int i = 0; i < modalButtons.size(); i++) {
                    org.openqa.selenium.WebElement button = modalButtons.get(i);
                    System.out.println("  " + (i+1) + ". Type: " + button.getAttribute("type") + 
                                     ", Class: " + button.getAttribute("class") + 
                                     ", Text: " + button.getText() +
                                     ", Visible: " + button.isDisplayed());
                }
            } catch (Exception debugE) {
                System.out.println("Button debug error: " + debugE.getMessage());
            }
            
            throw new RuntimeException("Tidak dapat klik tombol Kirim", e);
        }
    }
}
