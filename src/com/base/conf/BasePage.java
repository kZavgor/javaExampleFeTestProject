package com.base.conf;

import com.base.locators.CSS;
import com.base.locators.ID;
import com.base.locators.IXpath;
import com.base.locators.Locator;
import com.base.util.reporter.Reporter;
import com.base.util.Constants;
import com.base.util.Random;

import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BasePage {

    public static WebDriver driver = BaseTest.driver;

    /*
     * General actions
     */
    protected String getPageTitle(String message) {
        Reporter.log(message);
        return BaseTest.driver.getTitle();
    }

    protected void swithToFrame(String message, Locator frameName, Object... args) {
        Reporter.log(message);
        WebElement frame = getElement(frameName, args);
        BaseTest.driver.switchTo().frame(frame);
    }

    /**
     * Get Current Dat in dd format
     */
    protected String getCurrentDateInDDFormat() {
        DateFormat dateformat = new SimpleDateFormat("dd");
        Date date = new Date();
        String today = dateformat.format(date);
        return today;
    }

    /*
     * Work with elements
     */
    protected WebElement getElement(Locator locator, Object... args) {
        By by = locator.get(args);
        return BaseTest.driver.findElement(by);
    }

    protected List<WebElement> getElements(Locator locator, Object... args) {
        By by = locator.get(args);
        return BaseTest.driver.findElements(by);
    }

    protected String getElementAttributeValue(String message, String attributeName, Locator locator, Object... args) {
        Reporter.log(message);
        WebElement element = getElement(locator, args);
        return element.getAttribute(attributeName);
    }

    protected String getElementText(String message, Locator locator, Object... args) {
        Reporter.log(message);
        WebElement element = getElement(locator, args);
        return element.getText();
    }

    protected void setElementAttributeByXpathWithJS(String message, String attributeName, String attributeValue, IXpath locator, Object... args) {
        Reporter.log(message);
        String locatorXpath = locator.getXpath(args);
        String script = String.format("document.evaluate(\"%s\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.setAttribute('%s', '%s');",
                locatorXpath, attributeName, attributeValue);
        executeJS(script, locatorXpath);
    }

    /*
     * Drop down lists
     */
    protected void selectDropDownListOption(String message, String selectValue, Locator locator, Object... args) {
        Reporter.log(message);
        Select dropDownList = new Select(getElement(locator, args));
        dropDownList.selectByValue(selectValue);
    }

    protected void selectDropDownListOptionByIndex(String message, int itemIndex, Locator locator, Object... args) {
        Reporter.log(message);
        if (itemIndex > 0) {
            Select dropDownList = new Select(getElement(locator, args));
            dropDownList.selectByIndex(itemIndex - 1);
        }
    }

    protected void selectDropDownListOptionByText(String message, String selectItemText, Locator locator, Object... args) {
        Reporter.log(message);

        Select dropDownList = new Select(getElement(locator, args));
        WebElement select = getElement(locator, args);
        WebElement option = select.findElement(By.xpath(".//option[contains(text(),'" + selectItemText + "')]"));
        String optionValue = option.getAttribute("value");
        // if element has wrong value we can try select item only by text
        try {
            dropDownList.selectByValue(optionValue);
        } catch (NoSuchElementException e) {
            dropDownList.selectByVisibleText(selectItemText);
        }
    }

    protected int selectDropDownListRandomOption(String message, Locator locator, Object... args) {
        Reporter.log(message);
        Select dropDownList = new Select(getElement(locator, args));
        int size = dropDownList.getOptions().size();
        int selectedIndex = Random.genInt(0, size - 1);
        dropDownList.selectByIndex(selectedIndex);
        return selectedIndex;
    }

    /**
     * Select option from drop down
     *
     * @param message    Reporter log message
     * @param firstIndex Index of the first of drop down items from which select
     * @param lastIndex  Index of the last of drop down items from which select. If 'lastIndex' equals to -1
     *                   select from items starting from 'firstIndex' to the last item in the drop down
     * @param args       locator arguments
     * @return index of selected drop down item
     */
    protected int selectDropDownListRandomOption(String message, int firstIndex, int lastIndex, Locator locator, Object... args) {
        Reporter.log(message);
        Select dropDownList = new Select(getElement(locator, args));
        int size = dropDownList.getOptions().size();
        int selectedIndex = (lastIndex > 0)
                ? Random.genInt(firstIndex - 1, lastIndex - 1)
                : Random.genInt(firstIndex - 1, size - 1);
        dropDownList.selectByIndex(selectedIndex);
        return selectedIndex;
    }

    protected int selectDropDownListRandomOptionByIdWithJS(String message, ID id) {
        Reporter.log(message);
        String js = String.format("var x = document.getElementById(\"%s\").options.length; \n" +
                "var randomOption = Math.round(Math.random() * (x - 2)) + 1; \n" +
                "document.getElementById(\"%s\").options[randomOption].selected = true; \n" +
                "return randomOption;", id.toString(), id.toString());
        return Integer.parseInt(((JavascriptExecutor) BaseTest.driver).executeScript(js).toString());
    }

    protected String getDropDownListSelectedValueText(String message, Locator locator, Object... args) {
        Reporter.log(message);
        return new Select(getElement(locator, args)).getFirstSelectedOption().getText();
    }

    protected List<String> getDropDownListItemsValueTexts(String message, Locator locator, Object... args) {
        Reporter.log(message);
        List<WebElement> options = new Select(getElement(locator, args)).getOptions();
        List<String> items = new ArrayList<String>();
        for (WebElement option : options)
            items.add(option.getText());
        return items;
    }

    protected int getDropDownListItemsCount(String message, Locator locator, Object... args) {
        Reporter.log(message);
        return new Select(getElement(locator, args)).getOptions().size();
    }

    /*
     * Input fields and textareas
     */
    protected void type(String message, String value, Locator locator, Object... args) {
        Reporter.log(message);
        WebElement inputElement = getElement(locator, args);
        inputElement.clear();
        inputElement.sendKeys(value);
    }

    protected void append(String message, String value, Locator locator, Object... args) {
        Reporter.log(message);
        WebElement inputElement = getElement(locator, args);
        inputElement.sendKeys(value);
    }

    protected void typeWithJS(String message, String value, IXpath locator, Object... args) {
        Reporter.log(message);
        String locatorXpath = locator.getXpath(args);
        executeJS("document.evaluate(\"" + locatorXpath + "\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.value=\"" + value + "\";", locatorXpath);
    }

    protected String getTextFromInput(String message, Locator locator, Object... args) {
        return getElementAttributeValue(message, "value", locator, args);
    }

    protected void typeWithWipe(String message, String value, Locator locator, Object... args) {
        Reporter.log(message);
        WebElement inputElement = getElement(locator, args);
        String oldValue = inputElement.getAttribute("value");
        // simulate user interaction during clearing the input
        for (int i = 1; i <= oldValue.length(); i++)
            inputElement.sendKeys(Keys.BACK_SPACE);
        // type new value
        inputElement.sendKeys(value);
    }

    /*
     * Checkboxes
     */
    protected boolean isCheckboxChecked(Locator locator, Object... args) {
        return getElement(locator, args).isSelected();
    }

    protected void setCheckboxState(String message, boolean checked, Locator locator, Object... args) {
        if (checked ^ isCheckboxChecked(locator, args)) {
            click(message, locator, args);
        }
    }

    /*
     * Clicks
     */
    protected void click(String message, Locator locator, Object... args) {
        Reporter.log(message);
        WebElement element = getElement(locator, args);
        element.click();
    }

    protected void clickByXpathWithJS(String message, IXpath locator, Object... args) {
        Reporter.log(message);
        String locatorXpath = locator.getXpath(args);
        executeJS("document.evaluate(\"" + locatorXpath + "\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click();", locatorXpath);
    }

    protected void clickAndWaitElementVisibility(String message, Locator locator, Locator locatorWaitFor, Object... elemArgs) {
        click(message, locator, elemArgs);
        waitForElementVisibility(locatorWaitFor);

    }

    protected void clickAndWaitElementInvisibility(String message, Locator locator, Locator locatorWaitFor, Object... elemArgs) {
        click(message, locator, elemArgs);
        waitForElementInvisibility(locatorWaitFor);
    }

    /*
     * Count elements
     */
    protected int getElementsCount(Locator locator, Object... args) {
        return getElementsCountWithWait(0, locator, args);
    }

    protected int getElementsCountWithWait(int waitInSeconds, Locator locator, Object... args) {
        BaseTest.driver.manage().timeouts().implicitlyWait(waitInSeconds, TimeUnit.SECONDS);
        int size = getElements(locator, args).size();
        BaseTest.driver.manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        return size;
    }

    protected boolean isElementPresent(Locator locator, Object... args) {
        return (getElementsCount(locator, args) > 0);
    }

    protected boolean isElementPresentWithWait(int waitInSeconds, Locator locator, Object... args) {
        return (getElementsCountWithWait(waitInSeconds, locator, args) > 0);
    }

    protected boolean isElementVisible(Locator locator, Object... args) {
        return isElementVisibleWithWait(0, locator, args);
    }

    protected boolean isElementVisibleWithWait(int waitInSeconds, Locator locator, Object... args) {
        By by = locator.get(args);
        WebDriverWait wait = new WebDriverWait(BaseTest.driver, waitInSeconds);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable th) {
            return false;
        }
        return true;
    }

    protected String getElementCssValue(String message, String attributeName, Locator locator, Object... args) {
        Reporter.log(message);
        return getElement(locator, args).getCssValue(attributeName);
    }

    /*
     * Element waits
     */

    protected void switchToNewTab() {
        String winHandleBefore = BaseTest.driver.getWindowHandle();

        for (String winHandle : BaseTest.driver.getWindowHandles()) {
            if (!winHandle.equals(winHandleBefore)) BaseTest.driver.switchTo().window(winHandle);
        }
    }

    public int getCurrentHandlesSize() {
        return BaseTest.driver.getWindowHandles().size();
    }

    public void switchToWindow(String handler) {
        BaseTest.driver.switchTo().window(handler);
    }

    protected void waitForElementToBeClickable(Locator locator, Object... args) {
        By by = locator.get(args);
        WebDriverWait wait = new WebDriverWait(BaseTest.driver, Constants.ELEMENT_TIMEOUT_SECONDS);
        wait.until(ExpectedConditions.elementToBeClickable(by));

        //wait until element will be at the same place (for moving elements: for Chrome and IE)
        Point currLocation, newLocation;
        long startTime = System.currentTimeMillis();
        long delta;

        newLocation = new Point(-1, -1);
        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ie) {
            }   //the element may move veeery slowly. It's better to wait some time
            currLocation = newLocation;
            newLocation = getElement(locator, args).getLocation();
            delta = System.currentTimeMillis() - startTime;
        } while ((currLocation.getX() - newLocation.getX() != 0 && currLocation.getY() - newLocation.getY() != 0)
                && (delta <= Constants.ELEMENT_TIMEOUT_SECONDS * 1000));

        if (delta > Constants.ELEMENT_TIMEOUT_SECONDS * 1000) {
            throw new InvalidElementStateException("Element did not stand at the same place for " + Constants.ELEMENT_TIMEOUT_SECONDS + " seconds");
        }
        if (System.getProperty("browser", "firefox").equalsIgnoreCase("firefox")) {
            wait(Constants.ELEMENT_MICRO_TIMEOUT_SECONDS);
        }
    }

    protected void waitForElementPresent(Locator locator, Object... args) {
        By by = locator.get(args);
        WebDriverWait wait = new WebDriverWait(BaseTest.driver, Constants.ELEMENT_TIMEOUT_SECONDS);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    protected void waitForElementNotPresent(Locator locator, Object... args) {
        By by = locator.get(args);
        WebDriverWait wait = new WebDriverWait(BaseTest.driver, Constants.ELEMENT_TIMEOUT_SECONDS);
        wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(by)));
    }

    protected void waitForElementPresent(int timeout, Locator locator, Object... args) {
        By by = locator.get(args);
        WebDriverWait wait = new WebDriverWait(BaseTest.driver, timeout);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    protected void waitForElementVisibility(Locator locator, Object... args) {
        By by = locator.get(args);
        WebDriverWait wait = new WebDriverWait(BaseTest.driver, Constants.ELEMENT_TIMEOUT_SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected void waitForElementInvisibility(Locator locator, Object... args) {
        waitForElementInvisibilityWithWait(0, locator, args);
    }

    protected void waitForElementInvisibilityWithWait(int waitInSecondsBefore, Locator locator, Object... args) {
        By by = locator.get(args);
        BaseTest.driver.manage().timeouts().implicitlyWait(waitInSecondsBefore, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(BaseTest.driver, Constants.ELEMENT_TIMEOUT_SECONDS);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        BaseTest.driver.manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }

    protected void waitForElementInvisibilityWithWait(int waitInSecondsBefore, int waitInSeconds, Locator locator, Object... args) {
        By by = locator.get(args);
        BaseTest.driver.manage().timeouts().implicitlyWait(waitInSecondsBefore, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(BaseTest.driver, waitInSeconds);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        driver.manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }

    public void wait(int waitInSeconds) {
        try {
            Thread.sleep(waitInSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
     * Actions with mouse
     */

    /**
     * Move real mouse cursor to (0; 0)
     */
    protected void moveMouseOut() {
        try {
            Robot robot = new Robot();
            robot.mouseMove(0, 0);
        } catch (AWTException e) {
            Reporter.log("# A problem occurred during trying to move cursor out of the browser window!");
            e.printStackTrace();
        }
    }

    protected void mouseMove(String message, Locator locator, Object... args) {
        Reporter.log(message);
        moveMouseOut();
        new Actions(BaseTest.driver).moveToElement(getElement(locator, args)).build().perform();
    }

    protected void mouseMove(String message, int offsetX, int offsetY, Locator locator, Object... args) {
        Reporter.log(message);
        new Actions(BaseTest.driver).moveToElement(getElement(locator, args), offsetX, offsetY).build().perform();
    }

    protected void mouseOverByXpathUsingJS(String message, IXpath locator, Object... args) {
        Reporter.log(message);
        String locatorXpath = locator.getXpath(args);
        String elem = "document.evaluate(\"" + locatorXpath + "\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue";
        String javaScript = "if(document.createEvent) {" +
                "    var evObj = document.createEvent('MouseEvents');" +
                "    evObj.initEvent('mouseover', true, false);" +
                "    " + elem + ".dispatchEvent(evObj);" +
                "} else if(document.createEventObject) {" +
                "    " + elem + ".fireEvent('onmouseover');" +
                "}";
        executeJS(javaScript, locatorXpath);
    }

    protected void pressEnter(Locator locator, Object... args) {
        WebElement element = getElement(locator, args);
        element.sendKeys(Keys.ENTER);

    }

    protected void mouseOutByXpathUsingJS(String message, IXpath locator, Object... args) {
        Reporter.log(message);
        String locatorXpath = locator.getXpath(args);
        String elem = "document.evaluate(\"" + locatorXpath + "\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue";
        String javaScript = "if(document.createEvent) {" +
                "    var evObj = document.createEvent('MouseEvents');" +
                "    evObj.initEvent('mouseout', true, false);" +
                "    " + elem + ".dispatchEvent(evObj);" +
                "} else if(document.createEventObject) {" +
                "    " + elem + ".fireEvent('onmouseout');" +
                "}";
        executeJS(javaScript, locatorXpath);
    }

    protected void mouseDown(String message, Locator locator, Object... args) {
        Reporter.log(message);
        Locatable mouseDownItem = (Locatable) getElement(locator, args);
        Mouse mouse = ((HasInputDevices) BaseTest.driver).getMouse();
        mouse.mouseDown(mouseDownItem.getCoordinates());
    }

    protected void mouseUp(String message, Locator locator, Object... args) {
        Reporter.log(message);
        Locatable mouseDownItem = (Locatable) getElement(locator, args);
        Mouse mouse = ((HasInputDevices) BaseTest.driver).getMouse();
        mouse.mouseUp(mouseDownItem.getCoordinates());
    }

    protected void mouseScroll(int x, int y) {
        JavascriptExecutor js = (JavascriptExecutor) BaseTest.driver;
        Reporter.log(String.format("Scrolling mouse to coordinates: %s, %s", x, y));
        js.executeScript("scroll(" + x + ", " + y + ");");
    }

    protected void mouseScroll(int x, int y, Locator locator, Object... args) {
        WebElement element = getElement(locator, args);
        x += element.getLocation().getX();
        y += element.getLocation().getY();
        JavascriptExecutor js = (JavascriptExecutor) BaseTest.driver;
        Reporter.log(String.format("Scrolling mouse to coordinates: %s, %s", x, y));
        js.executeScript("scroll(" + x + ", " + y + ");");
    }

    protected void mouseDragAndDrop(String message, WebElement elementToDrag, WebElement dragTo) {
        Reporter.log(message);
        new Actions(BaseTest.driver).clickAndHold(elementToDrag).moveToElement(dragTo).release().build().perform();
    }

    /**
     * This method add element xpath to exception message (for debug)
     * If we can not add xpath to exception message -
     * returns unchanged exception. (It's impossible, because we get field from Throwable:) )
     *
     * @param script
     * @param xpath
     */
    private void executeJS(String script, String xpath) {
        try {
            ((JavascriptExecutor) BaseTest.driver).executeScript(script);
        } catch (WebDriverException wd) {
            Field f = null;
            try {
                f = Throwable.class.getDeclaredField("detailMessage");
            } catch (NoSuchFieldException e) {
                throw wd;
            }
            f.setAccessible(true);
            try {
                String error = "\nXPath: " + xpath + "\n" + f.get(wd);
                f.set(wd, error);
            } catch (IllegalAccessException ia) {
            }
            throw wd;
        }
    }

    public void fillFileSelectorForm(String filepath) {
        StringSelection ss = new StringSelection(filepath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

        //imitate mouse events like ENTER, CTRL+C, CTRL+V
        Robot robot = null;
        try {
            robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void refresh() {
        driver.navigate().refresh();
    }
}
