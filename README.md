# 🧪 Refinds Testing Project - Order Flow

Project ini merupakan implementasi pengujian otomatis berbasis **POM + Cucumber + Selenium** untuk skenario end-to-end pemesanan produk dan pembatalan pesanan pada sistem e-commerce Refinds.

---

---

## 🚀 Tools & Frameworks

- 🧪 **Cucumber**: Mendefinisikan skenario test dalam format Gherkin (`.feature`)
- 🕹️ **Selenium WebDriver**: Untuk melakukan interaksi pada browser
- ☕ **Java**: Bahasa utama pengembangan test
- 📦 **JUnit/TestNG**: (opsional) sebagai test runner
- 🔧 **Page Object Model (POM)**: Untuk modular dan reusable code

---

## 📌 Fitur yang Diuji

- Login user
- Pemesanan produk
- Pengisian nomor WhatsApp
- Navigasi ke halaman Transaksi
- Pembatalan pesanan terakhir
- Logout pengguna

---


## 📁 Struktur Folder

```
src
├── main
│ ├── java
│ │ └── org.example
│ │ └── Main.java 
│ └── resources 
│
├── test
│ ├── java
│ │ ├── pages # Kumpulan Page Object
│ │ │ ├── BasePage.java # Kelas dasar untuk utilitas Selenium (wait, click, dll.)
│ │ │ ├── DetailProductPage.java # Interaksi pada halaman detail produk
│ │ │ ├── HomePage.java # Interaksi pada halaman home
│ │ │ ├── LoginPage.java # Interaksi pada halaman login
│ │ │ ├── TransaksiPage.java # Interaksi pada halaman transaksi/pesanan
│ │ │ └── WhatsAppPage.java # Interaksi pada halaman WhatsApp
│ │ │
│ │ ├── runners
│ │ │ └── TestRunner.java # Kelas untuk menjalankan semua fitur dengan Cucumber
│ │ │
│ │ ├── stepdefinitions # Implementasi dari setiap step di feature file
│ │ │ ├── DetailProductSteps.java
│ │ │ ├── LoginSteps.java
│ │ │ ├── ProductSteps.java
│ │ │ ├── TransaksiPageSteps.java
│ │ │ └── WhatsAppPageSteps.java
│ │ │
│ │ └── utils
│ │   └── DriverManager.java # Kelas utilitas untuk inisialisasi dan manajemen WebDriver
│
├── resources # Tempat untuk file .feature, konfigurasi test
│
└── features
  └── order_flow.feature # File skenario pengujian Gherkin (Order Flow)
```

## ✔️ Hasil Test
### Passed Test Steps:
✔  User logs in with valid credentials

✔  User clicks a product from the Homepage

✔  User clicks the Pesan button on the Product Detail page

✔  User click the Pesan button and enter the WhatsApp page

✔ User open Transaksi page from Product Detail page

✔ User cancel the order from the Transaksi page

✔ User successfully Sign Out

🟢 Kesimpulan: Semua skenario pengujian berjalan dengan sukses tanpa ada error atau kegagalan.