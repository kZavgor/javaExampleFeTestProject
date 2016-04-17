package com.base.locators;

import org.openqa.selenium.By;

/**
 * Represents a Locator object that will be used Selenium WebDriver for access to elements
 * of the application.
 */
public abstract class Locator {

    /**
     * Represents a type locator that will be used Selenium WebDriver
     */
    public enum Type {

        /**
         * XPath locator type
         */
        XPATH,

        /**
         * CSS locator type
         */
        CSS,

        /**
         * ID locator type
         */
        ID,

        /**
         * NAME locator type
         */
        NAME,

        /**
         * CSS locator type
         */
        CLASSNAME
    }

    private final Type type;
    private final String value;

    /**
     * Initialize a Locator object.
     *
     * @param type of locator
     * @param value of locator for accessing to element.
     */
    public Locator(Type type, String value) {
        this.type = type;
        this.value = value;
    }

    /**
     * The method return By object for find element by determined locator
     *
     * @param args
     * @return By object
     */
    public By get(Object ... args) {
        switch (type) {
            case XPATH:
                return By.xpath(String.format(value, args));
            case CSS:
                return By.cssSelector(String.format(value, args));
            case ID:
                return By.id(String.format(value, args));
            case NAME:
                return By.name(String.format(value, args));
            case CLASSNAME:
                return By.className(String.format(value, args));
        }
        throw new IllegalLocatorException(String.format("Locator type \"%s\" is unknown!", type));
    }

    /**
     * The method return locator type
     *
     * @return locator type
     */
    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return value;
    }


    public String toString(Object ... args) {
        return String.format(value, args);
    }
}
