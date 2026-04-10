package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.RegistrationPage;
import utils.DriverFactory;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RegistrationTest {
  private static WebDriver driver;
  private static RegistrationPage regPage;

  @BeforeAll
  static void setup() {
    driver = DriverFactory.getDriver("chrome", "headless");
    // Pulling staging URL from GitHub secrets to avoid hardcoding
    String baseUrl = System.getenv("STAGING_URL") != null ? System.getenv("STAGING_URL")
        : "http://staging.paymentgw.internal";
    driver.get(baseUrl + "/register");
    regPage = new RegistrationPage(driver);
  }

  @Test
  @Order(1)
  @DisplayName("TC-12: Valid new user registration returns success")
  void testValidRegistration() {
    regPage.enterEmail("testuser_" + System.currentTimeMillis() + "@qa.test");
    regPage.enterPassword("SecurePass#99");
    regPage.clickSubmit();

    String msg = regPage.getSuccessMessage();
    assertTrue(msg.contains("Registration successful"), "Expected success message but got: " + msg);
  }

  @Test
  @Order(2)
  @DisplayName("TC-13: Duplicate email registration returns 409 error")
  void testDuplicateEmailRejected() {
    regPage.enterEmail("existing@qa.test");
    regPage.enterPassword("AnotherPass#1");
    regPage.clickSubmit();

    String error = regPage.getErrorMessage();
    assertTrue(error.contains("already registered"), "Expected duplicate error but got: " + error);
  }

  @AfterAll
  static void tearDown() {
    DriverFactory.quitDriver();
  }
}