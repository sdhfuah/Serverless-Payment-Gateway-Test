package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegistrationPage {
  private final WebDriver driver;
  private final WebDriverWait wait;

  // Page object for login/registration. TODO: Move selectors to a properties file
  // later if UI changes again.
  private final By emailField = By.id("reg-email");
  private final By passwordField = By.id("reg-password");
  private final By submitButton = By.id("reg-submit");
  private final By successMsg = By.id("reg-success");
  private final By errorMsg = By.id("reg-error");

  public RegistrationPage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  public void enterEmail(String email) {
    // Using visibilityOfElementLocated here because the CI headless browser is slow
    // to render the DOM.
    wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
  }

  public void enterPassword(String password) {
    driver.findElement(passwordField).sendKeys(password);
  }

  public void clickSubmit() {
    driver.findElement(submitButton).click();
  }

  public String getSuccessMessage() {
    return wait.until(ExpectedConditions.visibilityOfElementLocated(successMsg)).getText();
  }

  public String getErrorMessage() {
    return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMsg)).getText();
  }
}