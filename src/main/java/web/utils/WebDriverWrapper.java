package web.utils;

import api.utils.ReportWriter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxim on 1/28/2017.
 */
public class WebDriverWrapper extends EventFiringWebDriver implements WebDriver {
    private WebDriver driver;
    private EventFiringWebDriver efwd;
    private static final long MAX_TIMEOUT = 10;
    private static Wait<WebDriver> wait;


    public WebDriverWrapper(WebDriver driver) {
        super(driver);
        this.driver = driver;
        EventHandler eventListener = new EventHandler(this.driver);
        this.efwd = new EventFiringWebDriver(this.driver);
        efwd.register(eventListener);
        wait = new WebDriverWait(driver, MAX_TIMEOUT);
    }

    public WebElement findElement(By by) {
        if (waitAndVerifyElementPresent(by)) {
            return efwd.findElement(by);
        } else {
            ReportWriter.logError("Element '" + by + "' isn't find");
            return null;
        }
    }


    public List<WebElement> findElements(By by) {
        if (waitAndVerifyElementsPresent(by)) {
            return efwd.findElements(by);
        } else {
            ReportWriter.logError("FindElements find nothing by element'" + by + "'");
            return new ArrayList();
        }
    }

    public boolean waitAndVerifyElementPresent(final WebElement webElement) {
        try {
            wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver dr) {
                    return (webElement != null);
                }
            });
        } catch (Exception e) {
            ReportWriter.logException("webElement '" + webElement + "' isn't present on Page");
//            ReportWriter.logException("Exception message" + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean waitAndVerifyElementPresent(final By by) {
        try {
            wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver dr) {
                    return ((dr.findElement(by) != null));
                }
            });
        } catch (Exception e) {
            ReportWriter.logException("Element '" + by + "' isn't present on Page");
//            ReportWriter.logException("Exception message" + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean waitAttributePresent(final By by, final String attributeName) {
        try {
            wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver dr) {
                    return (dr.findElement(by).getAttribute(attributeName)!=null && !dr.findElement(by).getAttribute(attributeName).equals(""));
                }
            });
        } catch (Exception e) {
            ReportWriter.logException("Attribute '" + attributeName + "' for Element '" + by + "' is null or empty");
            return false;
        }
        return true;
    }

    public boolean waitAttributePresentAndContains(final By by, final String attributeName, final String attributeValue) {
        try {
            wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver dr) {
                    return (dr.findElement(by).getAttribute(attributeName)!=null && !dr.findElement(by).getAttribute(attributeName).equals("") && dr.findElement(by).getAttribute(attributeName).contains(attributeValue));
                }
            });
        } catch (Exception e) {
            ReportWriter.logException("Attribute '" + attributeName + "' for Element '" + by + "' is null or empty or doesn't contain value '" + attributeValue + "'");
            ReportWriter.logException("Attribute '" + attributeName + "' = [" + driver.findElement(by).getAttribute(attributeName) + "]");
            return false;
        }
        return true;
    }

    public boolean waitUntilElementDisplayNone(final By by) {
        try {
            wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver dr) {
                    return (driver.findElement(by).getAttribute("style").contains("display: none;"));
                }
            });
        } catch (Exception e) {
            ReportWriter.logException(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean waitUntilElementContainsNotEmptyValue(final By by) {
        try {
            wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver dr) {
                    return (!(dr.findElement(by).getAttribute("value").equals("")));
                }
            });
        } catch (Exception e) {
            ReportWriter.logException("Element '" + by + "' doesn't contain any Value");
            return false;
        }
        return true;
    }

    public boolean waitAndVerifyElementsPresent(final By by) {
        try {
            wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver dr) {

                    return (dr.findElements(by).size() > 0);
                }
            });
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean waitAndVerifyElementIsClickable(final By by) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Exception e) {
            ReportWriter.logException("Element '" + by + "' isn't clickable");
//            ReportWriter.logException("Exception message" + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean waitUrlLoaded() {
        try {
            wait.until(ExpectedConditions.urlMatches("http*"));
        } catch (Exception e) {
            ReportWriter.logException("URl is not loaded");
            return false;
        }
        return true;
    }

    public boolean waitAndVerifyElementIsClickable(final WebElement webElement) {
        wait = new WebDriverWait(driver, 1);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
        } catch (Exception e) {
            ReportWriter.logException("Element '" + webElement + "' isn't clickable");
//            ReportWriter.logException("Exception message" + e.getMessage());
            wait = new WebDriverWait(driver, MAX_TIMEOUT);
            return false;
        }
        wait = new WebDriverWait(driver, MAX_TIMEOUT);
        return true;
    }

    public boolean waitAndVerifyElementIsVisible(final By by) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            ReportWriter.logException("Element '" + by + "' isn't visible");
//            ReportWriter.logException("Exception message" + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean waitAndVerifyElementIsVisible(final WebElement webElement) {
        try {
            wait.until(ExpectedConditions.visibilityOf(webElement));
        } catch (Exception e) {
            ReportWriter.logException("Element '" + webElement + "' isn't visible");
//            ReportWriter.logException("Exception message" + e.getMessage());
            return false;
        }
        return true;
    }

    public WebElement findElementClickable(final By by) {
        if (waitAndVerifyElementIsClickable(by)) {
            return efwd.findElement(by);
        } else {
            ReportWriter.logError("Element '" + by + "' isn't find");
            return null;
        }
    }

    public boolean waitAndVerifyUntilInvisibilityOfElementLocated(final By by) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        } catch (Exception e) {
            ReportWriter.logException("Element '" + by + "' is still Visible");
//            ReportWriter.logException("Exception message" + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean waitAndVerifyUntilInvisibilityOfElementLocated(final By by, int timeout) {
        wait = new WebDriverWait(driver, timeout);
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        } catch (Exception e) {
            ReportWriter.logException("Element '" + by + "' is still Visible");
            wait = new WebDriverWait(driver, MAX_TIMEOUT);
            return false;
        }
        wait = new WebDriverWait(driver, MAX_TIMEOUT);
        return true;
    }

    public void wait(int millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
        }
    }
}
