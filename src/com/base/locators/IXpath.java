package com.base.locators;

/**
 * Represents a XPath interface for further implementation
 * in the class ID and the get XPath locator type from ID
 * to work with javascript
 */
public interface IXpath {

    /**
     * The method returns value of XPath locator
     *
     * @param args
     * @return value of XPath locator
     */
    public String getXpath(Object ... args);

}
