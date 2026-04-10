package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

public class SearchPage {
  private final WebDriver driver;

  // Locators for the search functionality
  private final By searchInput = By.id("service-search-bar");
  private final By searchButton = By.id("btn-search");
  private final By searchResults = By.className("search-result-item");

  public SearchPage(WebDriver driver) {
    this.driver = driver;
  }

  public void enterSearchKeyword(String keyword) {
    WaitUtils.waitForVisibility(driver, searchInput, 10).clear();
    driver.findElement(searchInput).sendKeys(keyword);
  }

  public void clickSearch() {
    WaitUtils.waitForClickable(driver, searchButton, 10).click();
  }

  public int getResultCount() {
    // Just checking how many items returned
    return driver.findElements(searchResults).size();
  }
}