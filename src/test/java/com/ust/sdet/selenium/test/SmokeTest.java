package com.ust.sdet.selenium.test;
import com.ust.sdet.selenium.support.Config;
import com.ust.sdet.selenium.support.DriverFactory;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import static org.junit.jupiter.api.Assertions.*;
public class SmokeTest
{
        private WebDriver driver;

        @BeforeEach
    void setUp()
        {
            driver= DriverFactory.createChromeDriver();
        }
        @AfterEach
        void tearDown()
        {
            if(driver !=null)
            {
                driver.quit();
            }
        }

        @Test
    @DisplayName("Product catalog loads ina real Chrome session")
    void catalogLoads()
        {
            driver.get(Config.catalogUrl());

            By CatalogHeading = By.cssSelector("[data-test='catalog-title']");
            assertAll(
                    ()-> assertTrue(driver.getTitle().contains("Catalog")),
                    ()->assertTrue(driver.findElement(CatalogHeading).isDisplayed()),
                    ()->assertEquals("Product Catalog",driver.findElement(CatalogHeading).getText())
            );
        }
}
