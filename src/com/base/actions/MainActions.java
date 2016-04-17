package com.base.actions;

import com.base.conf.BaseActions;
import com.base.conf.Pages;
import com.base.data.AttachmentData;
import com.base.data.EmailBodyLinkData;
import com.base.data.EmailData;


public class MainActions extends BaseActions {

    public void doLogin(String login, String password) {

        Pages.loginPage().openPage();
        Pages.loginPage().typeEmail(login);
        Pages.loginPage().clickSubmitEmail();
        Pages.loginPage().typePassword(password);
        Pages.loginPage().clickSignInButton();
        Pages.inboxPage().waitForPageToLoad();
    }

    public void sendEmail(EmailData emailData){

        Pages.inboxPage().clickSendMailButton();
        Pages.emailForm().waitForFormToLoad();

        Pages.emailForm().typeReceiverEmail(emailData.getReceiver());
        Pages.emailForm().typeEmailSubject(emailData.getSubject());

        for(Object object : emailData.getBody()) {
            if(object instanceof String) {
                Pages.emailForm().appendBody(String.valueOf(object));
                continue;
            }
            if(object instanceof AttachmentData) {
                addAttachment(AttachmentData.class.cast(object));
                continue;
            }
            if(object instanceof EmailBodyLinkData) {
                addLinkToEmailBody(EmailBodyLinkData.class.cast(object));
            }
        }
        Pages.emailForm().clickSendEmailButton();
    }

    public void addAttachment(AttachmentData attachmentData) {
        Pages.emailForm().clickAddFileToEmailButton();
        Pages.emailForm().fillFileSelectorForm(attachmentData.getAttachmentFilePath());
    }

    public void addLinkToEmailBody(EmailBodyLinkData emailBodyLinkData) {
        Pages.emailForm().clickAddLinkToEmailButton();
        Pages.emailForm().typeLinkText(emailBodyLinkData.getLinkText());
        Pages.emailForm().typeLinkValue(emailBodyLinkData.getLinkValue());
        Pages.emailForm().clickSubmitLinkButton();
    }

    public void openEmailBySubject(String subject) {
        Pages.inboxPage().waitForEmailWithSubject(subject);
        Pages.inboxPage().clickInboxEmailWithSubject(subject);
    }

}
