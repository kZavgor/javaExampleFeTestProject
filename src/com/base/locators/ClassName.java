package  com.base.locators;

/**
 * Represents a ClassName object that will be used Selenium WebDriver for access to elements
 * of the application.
 */
public class ClassName extends Locator {

    /**
     * Initialize a ClassName object.
     *
     * @param value is a class name of element.
     */
    public ClassName(String value) {
        super(Type.CLASSNAME, value);
    }
}
