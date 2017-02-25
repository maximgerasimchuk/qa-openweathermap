package web.webElements;

import web.utils.WebDriverFactory;
import web.utils.WebDriverWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxim on 1/28/2017.
 */
public class BaseElement {
    protected static WebDriverWrapper driver;
    protected static JavascriptExecutor executor;

    public BaseElement() {
        this.driver = WebDriverFactory.getDriverWrapper();
        executor = (JavascriptExecutor)driver;
    }

    public void click(By by) {
        waitAndVerifyElementPresent(by);
        driver.findElementClickable(by).click();
    }

    public void click(String parametrizedXpath) {
        waitAndVerifyElementPresent(By.xpath(parametrizedXpath));
        driver.findElementClickable(By.xpath(parametrizedXpath)).click();
    }

    public void clickJavascript(By by) {
        waitAndVerifyElementPresent(by);

        WebElement webElement = driver.findElement(by);
        executor.executeScript("arguments[0].click();", webElement);
    }

    public void clickJavascript(WebElement webElement) {
        waitAndVerifyElementPresent(webElement);

        executor.executeScript("arguments[0].click();", webElement);
    }

    public void clickJavascript(String parametrizedXpath) {
        waitAndVerifyElementPresent(By.xpath(parametrizedXpath));

        WebElement webElement = driver.findElement(By.xpath(parametrizedXpath));
        executor.executeScript("arguments[0].click();", webElement);
    }

    public void clickAction(String parametrizedXpath) {
        waitAndVerifyElementPresent(By.xpath(parametrizedXpath));

        Actions action = new Actions(driver);
        WebElement webElement = driver.findElement(By.xpath(parametrizedXpath));
        action.click(webElement).build().perform();
    }

    public void clickAction(By by) {
        waitAndVerifyElementPresent(by);

        Actions action = new Actions(driver);
        WebElement webElement = driver.findElement(by);
        action.click(webElement).build().perform();
    }

    public String getText(By by) {
        return driver.findElement(by).getText();
    }

    public void waitUntilInvisibilityOfElement(By by){
        driver.waitAndVerifyUntilInvisibilityOfElementLocated(by);
    }

    public void waitUntilElementDisplayNone(By by){
        driver.waitUntilElementDisplayNone(by);
    }

    public void waitUntilInvisibilityOfElement(By by, int timeout){
        driver.waitAndVerifyUntilInvisibilityOfElementLocated(by, timeout);
    }

    public boolean waitAndVerifyElementPresent(By by){
        return driver.waitAndVerifyElementPresent(by);
    }

    public boolean waitAndVerifyElementPresent(WebElement webElement){
        return driver.waitAndVerifyElementPresent(webElement);
    }

    public void waitUntilElementIsVisible(By by){
        driver.waitAndVerifyElementIsVisible(by);
    }

    public String getAttributeValue(By by, String attributeName){
        return driver.findElement(by).getAttribute(attributeName);
    }

    public String getAttributeValueWithWait(By by, String attributeName){
        driver.waitAttributePresent(by, attributeName);
        return driver.findElement(by).getAttribute(attributeName);
    }

    public String getAttributeValueWithContainsWait(By by, String attributeName, String attributeValue){
        driver.waitAttributePresentAndContains(by, attributeName, attributeValue);
        return driver.findElement(by).getAttribute(attributeName);
    }

    public String getCssValue(By by, String elementName){
        return driver.findElement(by).getCssValue(elementName);
    }

    public boolean isElementDisplay(By by){
        String style =  driver.findElement(by).getAttribute("style");
        if(style.equals("display: none;")){
            return false;
        }
        return true;
    }

    public boolean isElementPresent(By by){
        return driver.waitAndVerifyElementPresent(by);
    }

    public void waitAndVerifyElementContainsNotEmptyValue(By by){
        driver.waitUntilElementContainsNotEmptyValue(by);
    }

    public ArrayList<String> getAttributeValueList(By by, String elementName){
        List<WebElement> webElementArrayList = driver.findElements(by);
        ArrayList<String> attributeList = new ArrayList<String>();
        for(WebElement webElement: webElementArrayList ){
            attributeList.add(webElement.getAttribute(elementName));
        }
        return attributeList;
    }
}
