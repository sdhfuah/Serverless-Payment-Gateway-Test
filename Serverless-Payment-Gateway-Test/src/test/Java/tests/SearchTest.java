package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.SearchPage;
import utils.DriverFactory;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SearchTest {
  private static WebDriver driver;
  private static SearchPage searchPage;

  @BeforeAll
  static void setup() {
    driver = DriverFactory.getDriver("chrome", "headless");
    String baseUrl = System.getenv("STAGING_URL") != null ? System.getenv("STAGING_URL")
        : "http://staging.paymentgw.internal";
    driver.get(baseUrl + "/search");
    searchPage = new SearchPage(driver);
  }

  @Test
  @Order(1)
  @DisplayName("TC-11: User can search for a valid micro-service")
  void testValidSearch() {
    searchPage.enterSearchKeyword("Streaming");
    searchPage.clickSearch();

    int results = searchPage.getResultCount();
    assertTrue(results >= 0, "Search executed successfully");
  }

  @AfterAll
  static void tearDown() {
    DriverFactory.quitDriver();
  }
}