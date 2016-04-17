package com.base.pages;


import com.base.conf.BasePage;
import com.base.locators.CSS;
import com.base.locators.ID;
import com.base.locators.Locator;
import com.base.util.Constants;
import com.base.util.reporter.Reporter;

public class LoginPage extends BasePage {

    private Locator pageID = new CSS(".card.signin-card");
    private Locator emailInput = new ID("Email");
    private Locator submitEmail = new ID("next");
    private Locator passwordInput = new ID("Passwd");
    private Locator submitSignInFormButton = new ID("signIn");

    public void openPage() {
        driver.get(Constants.BASE_URL);
        Reporter.log("Open main page");
        waitForElementVisibility(pageID);
    }

    public void typeEmail(String email) {
        waitForElementVisibility(emailInput);
        type("Type email: " + email, email, emailInput);
    }

    public void clickSubmitEmail() {
        waitForElementToBeClickable(submitEmail);
		click("Click submit email button", submitEmail);
    }

    public void typePassword(String password) {
        waitForElementVisibility(passwordInput);
        type("Type password: " + password, password, passwordInput);
    }

    public void clickSignInButton() {
        waitForElementToBeClickable(submitSignInFormButton);
        click("Click sign in button", submitSignInFormButton);
    }





}
