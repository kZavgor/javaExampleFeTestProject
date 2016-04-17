package com.base.locators;

/**
 * Represents a CSS locator type that will be used Selenium WebDriver for access to elements
 * of the application.
 */
public class CSS extends Locator {

    /**
     * Initialize a CSS object.
     *
     * @param value is a value of locator for accessing to element.
     */
    public CSS(String value) {
        super(Type.CSS, value);
    }
}
