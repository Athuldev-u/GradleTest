package com.ust.sdet.selenium.bdd;

import com.ust.sdet.selenium.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {
    private final World world;
    public LoginSteps(World world) {
        this.world = world;
    }

    @Given("the login page is open")
    public void theLoginPageIsOpen() {
        world.login = new LoginPage(world.driver).open();
    }


    @When("I enter email: {string}")
    public void iEnterEmailAndPassword(String querry) {
        world.login.fill_username(querry);
    }

    @When("I enter password: {string}")
    public void iPressTheSubmitButton(String querry) {
        world.login = world.login.fill_password(querry);
    }
    @Then("I press login button")
    public void ipressTheLoginButton()
    {
        world.login=world.login.signin();
    }
    @Then("The home page shows up")
    public void showsHomePage()
    {
        assertTrue(world.login.verify().contains("Welcome"));
    }

}
