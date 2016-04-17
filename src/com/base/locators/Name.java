package com.base.locators;

/**
 * Represents a Name locator type that will be used Selenium WebDriver for access to elements
 * of the application.
 */
public class Name extends Locator {

    /**
     * Initialize a Name object.
     *
     * @param value is a value of locator for accessing to element.
     */
    public Name(String value) {
        super(Type.NAME, value);
    }
}
