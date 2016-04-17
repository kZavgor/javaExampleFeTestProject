package com.base.pages;


import com.base.conf.BasePage;
import com.base.locators.CSS;
import com.base.locators.ID;
import com.base.locators.Locator;
import com.base.util.reporter.Reporter;

public class EmailForm extends BasePage {

	private Locator pageID = new CSS("div[role='dialog'] div[command='Files']");
    private Locator receiverEmailButton = new CSS("form > div[style='']");
	private Locator receiverEmailInput = new CSS("form textarea[name='to']");
    private Locator emailSubjectInput = new CSS("form input[name='subjectbox']");
    private Locator emailBodyInput = new CSS("div[role='dialog'] .editable[role='textbox']");
    private Locator emailSendButton = new CSS("div[role='dialog'] div[role='button'][data-tooltip*='(Ctrl'][data-tooltip*='Enter)']");
    private Locator addFileToEmailButton = new CSS("div[role='dialog'] div[command='Files']");
    private Locator addLinkToEmailButton = new CSS("div[role='dialog'] div[command='+link']");
    private Locator linkTextInput = new ID("linkdialog-text");
    private Locator linkValueInput = new ID("linkdialog-onweb-tab-input");
    private Locator submitLinkButton = new CSS("div[role='dialog'] button[name='ok']");

    public void waitForFormToLoad() {
        Reporter.log("Wait for send email form to load");
        waitForElementVisibility(pageID);
    }

    public void typeReceiverEmail(String receiverEmail){

        if(!isElementPresent(receiverEmailInput)){
            click("Click receiver email input overlay", receiverEmailButton);
            waitForElementPresent(receiverEmailInput);
        }
        type("Type receiver email: " + receiverEmail, receiverEmail, receiverEmailInput);
    }

    public void typeEmailSubject(String emailSubject){
        type("Type email subject: " + emailSubject, emailSubject, emailSubjectInput);
    }

    public void appendBody(String body) {
        waitForElementVisibility(emailBodyInput);
        append("Append body: " + body, body, emailBodyInput);
    }

    public void clickSendEmailButton() {
        click("Click send email button", emailSendButton);
    }

    public void clickAddFileToEmailButton() {
        click("Click add file to email button", addFileToEmailButton);
    }

    public void clickAddLinkToEmailButton() {
        click("Click add link to email button", addLinkToEmailButton);
    }

    public void typeLinkText(String linkText){
        waitForElementToBeClickable(linkTextInput);
        type("Type link text: " + linkText, linkText, linkTextInput);
    }

    public void typeLinkValue(String linkValue){
        type("Type link value: " + linkValue, linkValue, linkValueInput);
    }

    public void clickSubmitLinkButton() {
        click("Click submit link button", submitLinkButton);
    }
}
