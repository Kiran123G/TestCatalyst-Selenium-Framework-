
 TestCatalyst Selenium Framework

TestCatalyst is a comprehensive and modular automation framework for web application testing using Selenium, TestNG, and ExtentReports. The framework is designed for scalability, maintainability, and ease of use, providing essential features for both functional and regression testing.

 Table of Contents
- [Introduction](#introduction)
- [Framework Features](#framework-features)
- [Project Structure](#project-structure)
- [Pre-requisites](#pre-requisites)
- [Setup](#setup)
- [Running Tests](#running-tests)
- [Reporting](#reporting)
- [Contributing](#contributing)
- [Author](#author)

 Introduction

TestCatalyst provides a robust foundation for automating web applications with a focus on modularity and ease of use. It supports multiple environments and browsers, making it suitable for large-scale projects.

 Framework Features
- Cross-Browser Support: Run tests across different browsers (Chrome, Firefox, Edge, etc.)
- TestNG Integration: Organize and execute test cases efficiently with TestNG.
- Extent Reports Integration: Detailed HTML reports for test results with screenshots on failure.
- Configuration Handling: Dynamic environment configurations for different environments (e.g., dev, stage, uat).
- Logging: Integrated logging system with Log4j2.
- Screenshot Utility**: Automatically capture screenshots for test failures.

 Project Structure

The project is organized in the following way:

```
src
├── main
│   └── java
│       ├── com.framework
│           ├── configurations      # Configuration handling (properties files)
│           ├── core                # Core components (WebDriverManager, BaseTest, etc.)
│           ├── pages               # Page Object Model classes
│           ├── utilities           # Utility classes (Waits, Excel Reader, Logger, etc.)
│           └── reporting           # Extent Reports and related classes
└── test
    └── java
        ├── com.framework.tests     # Test cases organized by type (functional, smoke, regression)
        └── resources
            ├── configs             # Configuration files for different environments (dev, stage, uat)
            ├── data                # Test data files
            ├── reports             # Generated test reports
            ├── screenshots         # Screenshots captured during test execution
            └── logs                # Log files

pom.xml                             # Maven dependencies
```

 Pre-requisites

Before setting up the project, ensure you have the following installed:
- Java 8+: The framework is built on Java.
- Maven: To handle dependencies and build the project.
- IDE: Eclipse, IntelliJ, or any IDE of your choice.
- Selenium WebDriver: For interacting with the web browsers.
- TestNG: A testing framework for running tests.
- ExtentReports: For generating HTML reports.

 Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/Kiran123G/TestCatalyst-Selenium-Framework.git
   ```
2. Navigate to the project folder:**
   ```bash
   cd TestCatalyst-Selenium-Framework
   ```
3. Open the project in your preferred IDE.
4. Install dependencies (if using Maven):
   ```bash
   mvn clean install
   ```

 Running Tests

To run the tests, use the following command (you can specify the environment by passing the `-stage` parameter):

```bash
mvn test -Denv=stage
```

You can also run tests through your IDE using TestNG integration.

 Reporting

After test execution, detailed HTML reports are generated in the `/reports` directory. The report includes:
- Test execution summary.
- Logs for each test step.
- Screenshots for failed tests.

 Contributing

I welcome contributions to improve this framework! If you’d like to help out, just fork the repository, make your changes, and then submit a pull request. Let’s work together to make this even better!



 Author

Kiran Ghuge  
QA Automation Engineer  
Creator of TestCatalyst Selenium Framework

This framework was created and developed by me, Kiran Ghuge, as a solution for building scalable and robust automation for web application testing. My goal was to create a framework that follows best practices, uses a modular design, and integrates with powerful reporting tools to make automation seamless and provide detailed insights through reporting.

