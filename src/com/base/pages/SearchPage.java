package com.base.pages;

import com.base.conf.BasePage;
import com.base.locators.CSS;
import com.base.locators.Locator;

public class SearchPage  extends BasePage{

    private Locator searchForm = new CSS("form[action='/search']");

    public boolean isPageLoaded() {
        switchToNewTab();
        return isElementPresent(searchForm);
    }


}
