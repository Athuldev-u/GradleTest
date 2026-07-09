package com.ust.sdet.selenium.test;

import com.ust.sdet.selenium.pages.CartPage;
import com.ust.sdet.selenium.pages.CatalogPage;
import com.ust.sdet.selenium.pages.LoginPage;
import com.ust.sdet.selenium.pages.ProductPage;
import com.ust.sdet.selenium.support.Config;
import com.ust.sdet.selenium.support.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.ust.sdet.selenium.pages.BasePage.*;
import java.time.Duration;
import java.util.List;

import static com.ust.sdet.selenium.test.LoginTest.BASE_URL;
import static org.junit.jupiter.api.Assertions.*;

public class CatalogPomTest {
    private WebDriver driver;
    @BeforeEach
    void setUp(){
        driver=DriverFactory.createChromeDriver();
    }
    @AfterEach
    void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }

    @Test
    @DisplayName("With Login page")
    void loginOops()
    {
        LoginPage login=new LoginPage(driver)
                .open().fill_username("customer@example.com").fill_password("Password@123").signin();
        assertEquals(BASE_URL+"/home",driver.getCurrentUrl());
        By user_ass=By.id("page-title");
        assertTrue(driver.findElement(user_ass).isDisplayed());
    }

    @Test
    @DisplayName("Search wait for catalog cards and validate matching result")
    void validateResult()
    {
        CatalogPage catalog=new CatalogPage(driver).open().searchFor("headphones","Showing 1 product");
        List<String> title=catalog.titles();
        assertFalse(title.isEmpty(),"search returned no products");
        assertTrue(title.stream().allMatch((t)->t.toLowerCase().contains("headphones")),"search result is not valid");

    }

    @Test
    @DisplayName("Exercise:POM sort hides the stale-element handling inside the page")
    void sortLowToHighOnPom() {
        List<Integer> prices = new CatalogPage(driver)
                .open()
                .sortBy("Price: Low to High")
                .prices();

        assertEquals(prices.stream().sorted().toList(), prices);
    }


    @Test
    @DisplayName("POM header component expresses cart badge and cart navigation")
    void headerComponentOpenCart() {
        CatalogPage catalog = new CatalogPage(driver).open();
        catalog.header().cartBadge().expectedCount(0);
        CartPage cart = catalog.header().openCart();

        assertEquals(0, cart.lineCount());
    }


    @Test
    @DisplayName("Catalog flow confirms the order from catalog to checkout")
    void catalogToConfirmOrder() {
        CatalogPage catalog = new CatalogPage(driver)
                .open()
                .searchFor("headphones", "Showing 1 product");

        ProductPage product = catalog.openFirstProduct();
        assertTrue(product.name().toLowerCase().contains("headphones"));

        CartPage cart = product.addToCart();
        cart.header().cartBadge().expectedCount(1);

        assertAll(
                () -> assertEquals(1, cart.lineCount()),
                () -> assertFalse(cart.total().isBlank())
        );

        String confirmation = cart.proceed().placeOrder().confirmationText();
        assertTrue(confirmation.toLowerCase().contains("confirmed"));
    }


}
