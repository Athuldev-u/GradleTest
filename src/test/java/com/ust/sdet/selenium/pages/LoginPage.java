package com.ust.sdet.selenium.pages;
import static com.ust.sdet.selenium.support.Config.*;

import com.ust.sdet.selenium.support.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.swing.plaf.PanelUI;
import java.security.PublicKey;

public class LoginPage extends BasePage{
    public static final By LOGIN_BUTTON=By.cssSelector("a[href='/login']");
    public  static final By USERNAME=By.id("email");
    public  static final By PASSWORD=By.id("password");
    private static final By SIGN_IN=By.cssSelector("button");
    private static final By LOGIN_TITLE=By.id("login-title");
    private static final By HOME_PAGE_TITLE=By.id("page-title");
    public LoginPage(WebDriver driver)
    {
        super(driver);
//        visible(LOGIN_TITLE);
    }
    public LoginPage open() {
        driver.get(Config.loginUrl());
        visible(LOGIN_TITLE);
        wait.until(ExpectedConditions.visibilityOfElementLocated(USERNAME));
        wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD));

        return this;
    }
    public LoginPage  fill_username(String querry)
    {
        type(USERNAME,querry,Keys.ENTER);
        return this;
    }
    public LoginPage fill_text(By by,String str)
    {
        type(by,str);
        return this;
    }
    public LoginPage  fill_password(String querry)
    {
        type(PASSWORD,querry,Keys.ENTER);
        return this;
    }

    public LoginPage signin()
    {
        click(SIGN_IN);
        wait.until(ExpectedConditions.urlContains("home"));
        return this;
    }
    public String verify()
    {
        return text(HOME_PAGE_TITLE);
    }








}
