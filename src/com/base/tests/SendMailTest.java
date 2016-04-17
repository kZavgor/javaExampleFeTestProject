package com.base.tests;

import com.base.conf.Actions;
import com.base.conf.BaseTest;
import com.base.conf.Pages;
import com.base.data.AttachmentData;
import com.base.data.EmailBodyLinkData;
import com.base.data.EmailData;
import com.base.util.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;


public class SendMailTest extends BaseTest {

    @Test
    public void doLogin() {
        Actions.mainActions().doLogin(Constants.DEFAULT_TEST_EMAIL, Constants.DEFAULT_TEST_PASSWORD);
    }

    @Test(dependsOnMethods = "doLogin")
    public void checkOverSizeAttachmentCannotBeSent() {
        Pages.inboxPage().clickSendMailButton();
        Pages.emailForm().waitForFormToLoad();

        Actions.mainActions().addAttachment(new AttachmentData());
        Assert.assertTrue(Pages.inboxPage().isOversizeAttachmentAlertAppeared(),
                "Service allows to attach files more then 25mb");
    }

    @Test(dependsOnMethods = "checkOverSizeAttachmentCannotBeSent")
    public void checkEmailBodyLinksOpenInNewTab() {
        Pages.inboxPage().refresh();
        Pages.inboxPage().waitForPageToLoad();

        EmailData emailData = new EmailData(Constants.DEFAULT_TEST_EMAIL, "Subject " + System.nanoTime(),
                "The link ", new EmailBodyLinkData(Constants.DEFAULT_TEST_LINK_TEXT, Constants.DEFAULT_TEST_LINK_VAUE),
                " follows to a Google search site");
        Actions.mainActions().sendEmail(emailData);
        Actions.mainActions().openEmailBySubject(emailData.getSubject());

        int tabsCountBefore = Pages.inboxPage().getCurrentHandlesSize();
        Pages.inboxPage().clickEmailBodyLink(Constants.DEFAULT_TEST_LINK_VAUE);
        Assert.assertTrue(tabsCountBefore < Pages.inboxPage().getCurrentHandlesSize(),
                "Link was not opened in a new tab.");
        Assert.assertTrue(Pages.searchPage().isPageLoaded(), "Search page is not loaded!");
    }
}
