package com.base.conf;

import com.base.pages.*;

public class Pages {

    private static LoginPage loginPage;
	private static InboxPage inboxPage;
	private static EmailForm emailForm;
	private static SearchPage searchPage;

    public static LoginPage loginPage(){
        if (loginPage == null) loginPage = new LoginPage();
        return loginPage;
    }

	public static InboxPage inboxPage(){
		if (inboxPage == null) inboxPage = new InboxPage();
		return inboxPage;
	}

	public static EmailForm emailForm(){
		if (emailForm == null) emailForm = new EmailForm();
		return emailForm;
	}

	public static SearchPage searchPage() {
		if (searchPage == null) searchPage = new SearchPage();
		return searchPage;
	}
}
