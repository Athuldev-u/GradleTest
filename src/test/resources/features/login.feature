Feature: User login
    As a Valid User
    i want to log in with my valid email and password
    so that i can shop product using my account

    Background:
        Given the login page is open

    @smoke
    Scenario:Login to valid user account
    When I enter email: "customer@example.com"
    And I enter password: "Password@123"
    Then I press login button
    Then the home page shows up
