package com.group5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FullTest {
  WebDriver driver;

  @BeforeEach
  public void setup() {
    // 自动安装和设置 Chrome 驱动
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--remote-allow-origins=*");
    // 这一行是为了防止有些电脑显卡报错，不影响测试
    options.addArguments("--disable-gpu");
    driver = new ChromeDriver(options);
  }

  @Test
  public void test1_Registration() {
    // 打开本地的 registration.html
    File file = new File("src/main/resources/registration.html");
    driver.get(file.toURI().toString());

    // 模拟用户输入
    driver.findElement(By.id("username")).sendKeys("Huangbao");
    driver.findElement(By.id("submitBtn")).click();

    // 这里的测试没有断言，只要不报错就算通过
  }

  @Test
  public void test2_Search() {
    File file = new File("src/main/resources/search.html");
    driver.get(file.toURI().toString());

    driver.findElement(By.id("searchBox")).sendKeys("Nintendo Switch");
    driver.findElement(By.id("searchBtn")).click();

    // 使用 Explicit Wait (显式等待)，这是作业要求的关键点！
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    boolean isDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("product-item")))
        .isDisplayed();

    assertTrue(isDisplayed, "搜索结果应该显示出来");
  }

  @Test
  public void test3_Checkout() {
    File file = new File("src/main/resources/checkout.html");
    driver.get(file.toURI().toString());

    driver.findElement(By.id("amount")).sendKeys("50.00");
    driver.findElement(By.id("payBtn")).click();

    // 再次使用显式等待
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    String msg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmation"))).getText();

    assertEquals("Success", msg, "支付后应该显示 Success");
  }

  @AfterEach
  public void tearDown() {
    // 每次测试完关闭浏览器
    if (driver != null) {
      driver.quit();
    }
  }
}