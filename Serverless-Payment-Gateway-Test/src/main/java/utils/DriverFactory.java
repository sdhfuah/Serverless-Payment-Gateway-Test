package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {
    private static WebDriver driver;

    // Handles WebDriver init. Setting up headless mode for CI/CD.
    public static WebDriver getDriver(String browser, String mode) {
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            if ("headless".equalsIgnoreCase(mode)) {
                options.addArguments("--headless=new");
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920,1080");
                // Had to add these to stop the Ubuntu container from crashing out of memory on GitHub Actions
                options.addArguments("--no-sandbox"); 
                options.addArguments("--disable-dev-shm-usage");
            }
            driver = new ChromeDriver(options);
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}