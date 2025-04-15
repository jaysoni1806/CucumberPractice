# 🚀 Redmine Automation Framework

This is a simple and powerful test automation framework created to test the **Redmine bug tracking tool** using:

- 🧪 **Cucumber (BDD) + TestNG**
- ☕ **Java + Maven**
- 📄 **Excel-based DataProvider**
- 📊 **Extent Report & Excel Report**
- 🧱 **Page Object Model (POM)** structure
- 📄 **Log4j** for logging execution details

---

## 📌 What This Framework Can Do

✅ Automates Redmine Login feature, CRUD operation on issue  
✅ Reads test data from Excel files (**data-driven testing**)  
✅ Generates beautiful **HTML reports** with test status  
✅ Creates an **Excel summary report** (pass/fail for each test case)  
✅ Clean code structure using **Page Object Model (POM)**  
✅ **Log4j Logging** to track step-by-step execution

---

## 🗂 Project Structure (Easy to Follow)
### 📁 Project Structure

```bash
CucumberPractice/
│
├── logs/
│   └── automation_09-04-2025.log
│
├── Report/
│   └── Extent/
│       └── Report_14-04-2025.html
│
├── src/
│   ├── main/java/
│   └── test/java/
│       ├── runners/
│       └── stepdefinitions/
│
├── resources/
│   ├── ExcelReport/
│   ├── External Files/
│   ├── features/
│   └── log4j2.xml
│
├── TestData/
│   └── AttachemtFiles/
│
├── target/
├── pom.xml
├── testng.xml
└── README.md
                   # Project documentation
