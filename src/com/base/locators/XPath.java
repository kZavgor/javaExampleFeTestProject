package com.base.locators;

/**
 * Represents a XPath locator type that will be used Selenium WebDriver for access to elements
 * of the application.
 */
public class XPath extends Locator implements IXpath {

    /**
     * Initialize a XPath object.
     *
     * @param value is a value of locator for accessing to element.
     */
    public XPath(String value) {
        super(Type.XPATH, value);
    }

    /**
     * The method returns a value of locator
     *
     * @param args
     * @return locator value
     */
    @Override
    public String getXpath(Object ... args) {
        return toString(args);
    }
}
