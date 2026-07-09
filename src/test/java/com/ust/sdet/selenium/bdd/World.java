package com.ust.sdet.selenium.bdd;
import com.ust.sdet.selenium.pages.*;
import com.ust.sdet.selenium.pages.component.Header;
import org.openqa.selenium.WebDriver;


public class World {
    public WebDriver driver;
    public CatalogPage catalog;
    public LoginPage login;
    public ProductPage product;
    public CartPage cart;
    public CheckoutPage  checkout;

    public Header header()
    {
        return new Header(driver);
    }

}
