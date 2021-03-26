package com.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

class LoginSofkaUTest {
    static WebDriver driver;

    @BeforeAll
    static void beforeAll() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
    }

    @Test
    void ingresarCredencialesValidas() {
        WebElement txtEmail = driver.findElement(By.id("email"));
        WebElement txtPassword = driver.findElement(By.id("passwd"));
        WebElement btnSubmit = driver.findElement(By.id("SubmitLogin"));

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);

        txtEmail.sendKeys("newemail@email.com");
        txtPassword.sendKeys("12345");
        btnSubmit.click();

        WebElement btnLogOut = wait.until(webDriver -> driver.findElement(By.xpath("//nav/div/a[@class='logout']")));

        Assertions.assertTrue(btnLogOut.isDisplayed());
    }

    @Test
    void ingresarCredencialesInvalidas() {
        WebElement txtEmail = driver.findElement(By.id("email"));
        WebElement txtPassword = driver.findElement(By.id("passwd"));
        WebElement btnSubmit = driver.findElement(By.id("SubmitLogin"));

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);

        txtEmail.sendKeys("newemail@email.com");
        txtPassword.sendKeys("12");
        btnSubmit.click();

        WebElement btnLogOut = wait.until(webDriver -> driver.findElement(By.xpath("//nav/div/a[@class='logout']")));

        Assertions.assertTrue(btnLogOut.isDisplayed());
    }

    @Test
    void ingresarEmailInvalido() {
        WebElement txtEmail = driver.findElement(By.id("email"));
        WebElement txtPassword = driver.findElement(By.id("passwd"));
        WebElement btnSubmit = driver.findElement(By.id("SubmitLogin"));

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class)
                .ignoring(IllegalArgumentException.class)
                .ignoring(TimeoutException.class);

        txtEmail.sendKeys("oldemail@email.com");
        txtPassword.sendKeys("12");
        btnSubmit.click();

        WebElement btnLogOut = wait.until(webDriver -> driver.findElement(By.xpath(null)));

        Assertions.assertTrue(btnLogOut.isDisplayed());
    }

    @AfterAll
    static void afterAll() {
        // driver.quit();
    }
}
