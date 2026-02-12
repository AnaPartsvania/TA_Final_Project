Author: Ana Partsvania

This is a comprehensive test automation framework built using Selenium WebDriver, Java, TestNG, and Allure reporting. The framework follows the Page Object Model (POM) design pattern and includes support for both UI and API testing.

| Technology         | Purpose                      |
|--------------------|------------------------------|
| Java 17+           | Programming Language         |
| Selenium WebDriver | Browser automation           |
| TestNG             | Test Framework & Execution   |
| Maven              | Build & Dependency Management |
| Allure Report      | Test Reporting & Analytics   |
| RestAssured        |API Testing                   |


## 🚀 Getting Started

### Prerequisites
* JDK 17 or higher
* Maven installed
* Chrome/Firefox browser installed

### Installation
1. Clone the repository:
   ```bash
   git clone [https://github.com/AnaPartsvania/TA_Final_Project
   ```
2. Navigate to the project directory
    ```bash
    cd your-repo-name
   ```
3. Install dependencies
   ```bash
   mvn clean install
   ```
   Run tests:
   ```bash
   mvn test
    ```

📊 Generating Allure Reports

Allure reports provide comprehensive test execution details including test steps, screenshots, API requests/responses, and more.
Generate and View Report (Recommended)

   After running tests:
    ```bash
    allure serve allure-results
    ````

This command will:
* Generate the HTML report from test results
* Start a local web server
* Automatically open the report in your default browser



