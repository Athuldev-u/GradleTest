package com.ust.sdet.selenium.test;

import com.ust.sdet.selenium.pages.LoginPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class LoginTest {
    private static WebDriver driver;
    private static WebDriverWait wait;
    public static final By LOGIN_BUTTON=By.cssSelector("a[href='/login']");
    public  static final By USERNAME=By.id("email");
    public  static final By PASSWORD=By.id("password");
    private static final By SIGN_IN=By.cssSelector("button");

    static String BASE_URL="http://localhost:5173";
    @BeforeEach
    void setup()
    {
        ChromeOptions option =new ChromeOptions();
        option.addArguments("--window:size=1440,900");
        driver=new ChromeDriver(option);
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @AfterEach
    void tear()
    {
        if(driver !=null)
        {
            driver.quit();
        }
    }
//    @Test
//    @DisplayName("login test ")
//    void loginTest()
//    {
//        driver.get(BASE_URL);
//        wait.until(ExpectedConditions.elementToBeClickable(LOGIN_BUTTON)).click();
//        WebElement email=wait.until(ExpectedConditions.visibilityOfElementLocated(USERNAME));
//        WebElement password=wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD));
//        email.clear();
//        email.sendKeys("customer@example.com");
//        password.clear();
//        password.sendKeys("Password@123");
//        WebElement signin=wait.until(ExpectedConditions.elementToBeClickable(SIGN_IN));
//        signin.click();
//        assertEquals(BASE_URL+"/home",driver.getCurrentUrl());
//        By user_ass=By.id("page-title");
//        assertEquals(driver.findElement(user_ass).getText(),"Welcome, Customer User");
////        assertTrue(driver.findElement(By.id("user-chip")).isDisplayed());
//
//
//    }
    @Test
    @DisplayName("With Login page")
    void loginOops()
    {
        LoginPage login=new LoginPage(driver)
                .open().fill_text(USERNAME,"customer@example.com").fill_text(PASSWORD,"Password@123").signin();
        assertEquals(BASE_URL+"/home",driver.getCurrentUrl());
        By user_ass=By.id("page-title");
        assertTrue(driver.findElement(user_ass).isDisplayed());
    }

}
