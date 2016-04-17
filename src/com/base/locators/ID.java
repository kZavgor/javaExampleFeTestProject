package com.base.locators;

/**
 * Represents a ID locator type that will be used Selenium WebDriver for access to elements
 * of the application.
 */
public class ID extends Locator implements IXpath {

    /**
     * Initialize a ID object.
     *
     * @param value is a value of locator for accessing to element.
     */
    public ID(String value) {
        super(Type.ID, value);
    }

    /**
     * The method returns a value of locator
     *
     * @param args
     * @return locator value
     */
    @Override
    public String getXpath(Object ... args) {
        return "//*[@id='" + toString() + "']";
    }
}
