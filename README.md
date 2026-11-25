# planer5D
## How to Run Tests

### Prerequisites
Before running the tests, make sure you have installed:

- Java 21 or higher
- IntelliJ IDEA
- Google Chrome
- Git

### 1. Start Chrome with Remote Debugging

The tests connect to an already running Chrome browser using Selenium Remote Debugging.

Close all Chrome instances first, then start Chrome with the following command in PowerShell:

```powershell
& "C:\Program Files\Google\Chrome\Application\chrome.exe" --remote-debugging-port=9222 --user-data-dir="C:\ChromeDebug"
```

**Note:** The browser must be started with the remote debugging port enabled. The tests cannot connect to a regular Chrome session.

### 2. Log in to Planner 5D

1. Open [Planner 5D](https://planner5d.com/).
2. Log in using your account credentials.
3. Make sure you are successfully logged in.
4. Keep the Chrome browser open while running the tests.

### 3. Run the Tests

1. Open the project in IntelliJ IDEA.
2. Wait until Gradle finishes synchronizing dependencies.
3. Navigate to the test class.
4. Run the test class or individual test methods using JUnit 5.

**Important:** Do not close the Chrome browser during test execution. Selenium uses the existing browser session.

### 4. Test Execution

The automated tests cover the following scenarios:

- Adding a sofa from the Planner 5D catalog to a project.
- Verifying that the sofa is visible in 3D mode.
- Verifying that the project API response contains the added sofa.

### Troubleshooting

- **Unable to connect to Chrome:** Make sure Chrome was started with `--remote-debugging-port=9222`.
- **Authentication issues:** Check that you are logged in to Planner 5D in the launched browser.
- **Browser connection errors:** Make sure no other Chrome instance is using the same debugging port.