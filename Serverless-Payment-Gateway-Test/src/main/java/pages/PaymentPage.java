package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class PaymentPage {
  private final WebDriver driver;
  private final WebDriverWait wait;

  private final By amountField = By.id("payment-amount");
  private final By payButton = By.id("pay-now");
  private final By confirmationId = By.id("confirmation-tx-id");
  private final By errorBanner = By.id("payment-error");

  public PaymentPage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  public void enterAmount(String amount) {
    wait.until(ExpectedConditions.visibilityOfElementLocated(amountField)).clear();
    driver.findElement(amountField).sendKeys(amount);
  }

  public void clickPay() {
    wait.until(ExpectedConditions.elementToBeClickable(payButton)).click();
  }

  public String getConfirmationTransactionId() {
    return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationId)).getText();
  }

  public String getErrorMessage() {
    return wait.until(ExpectedConditions.visibilityOfElementLocated(errorBanner)).getText();
  }
}