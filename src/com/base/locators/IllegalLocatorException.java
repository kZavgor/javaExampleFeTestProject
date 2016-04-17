package com.base.locators;

/**
 * Represents a IllegalLocatorException exception that will be throw if locator value be incorrect.
 */
public class IllegalLocatorException extends RuntimeException {

    /**
     * Initialize a IllegalLocatorException object.
     *
     * @param message will be displayed if locator value be incorrect
     */
    public IllegalLocatorException(String message) {
        super(message);
    }
}
