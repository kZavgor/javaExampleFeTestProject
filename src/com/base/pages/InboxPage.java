package com.base.pages;


import com.base.conf.BasePage;
import com.base.locators.CSS;
import com.base.locators.Locator;
import com.base.locators.XPath;
import com.base.util.Constants;
import com.base.util.reporter.Reporter;

public class InboxPage extends BasePage {

	private Locator pageID = new CSS("div[role='navigation'] a[href$='inbox']");
	private Locator sendMail = new XPath("//div[div[@role='navigation']]/div[@id]/div/div[@role='button']");
	private Locator inboxMailBySubject = new XPath("//table[colgroup]//div[@role='link' and contains(.,'%s')]");
	private Locator inboxMessageBodyLinkByValue = new CSS("table[role='presentation'] div[role='listitem'] a[href*='%s']");
	private Locator inboxMessagesLink = pageID;
	private Locator oversizeAttachmentAlert = new CSS("div[role='alertdialog']:not([aria-hidden]) a[href$='attach']");

	public void waitForPageToLoad() {
		Reporter.log("Wait for inbox page to load");
		waitForElementVisibility(pageID);
	}

	public void clickSendMailButton() {
		click("ClickSendMailButton", sendMail);
	}

	public void clickInboxEmailWithSubject(String subject) {
        waitForElementToBeClickable(inboxMailBySubject, subject);
		click("Click inbox email with subject: " + subject, inboxMailBySubject, subject);
	}

	public void waitForEmailWithSubject(String subject) {
		for (int i = 0; i < 10; i++) {
			clickInboxMessagesLink();
			if(!isElementPresent(inboxMailBySubject, subject)) {
				wait(Constants.ELEMENT_EXTRASMALL_TIMEOUT_SECONDS);
			} else break;

		}
 	}

	public void clickEmailBodyLink(String link) {
        waitForElementToBeClickable(inboxMessageBodyLinkByValue, link);
		click("Click email body link", inboxMessageBodyLinkByValue, link);
	}

	public void clickInboxMessagesLink() {
		click("Click inbox messages link", inboxMessagesLink);
	}

	public boolean isOversizeAttachmentAlertAppeared() {
		Reporter.log("Check if oversize attachment alert appeared");
		waitForElementPresent(Constants.ELEMENT_SMALL_TIMEOUT_MILLISECONDS, oversizeAttachmentAlert);
		return isElementVisible(oversizeAttachmentAlert);
	}


}
