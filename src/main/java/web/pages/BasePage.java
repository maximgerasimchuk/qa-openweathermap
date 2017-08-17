package web.pages;

import api.utils.ReportWriter;
import web.utils.WebDriverFactory;
import web.utils.WebDriverWrapper;
import web.webElements.BaseElement;
import web.webElements.DropDown;
import web.webElements.InputField;

/**
 * Created by maxim on 1/28/2017.
 */
public abstract class BasePage {
    protected String pageUrl;
    protected String basePageUrl;
    protected WebDriverWrapper driver;

    protected InputField inputField;
    protected BaseElement baseElement;
    protected DropDown dropDown;

    public boolean isOpened() {
        ReportWriter.logInfo("'" + pageUrl + "' is opened!");
        return driver.getCurrentUrl().contains(pageUrl);
    }

    public BasePage(WebDriverWrapper driver) {
        this.driver = driver;
        setElements(driver);
    }

    public BasePage(WebDriverWrapper driver, String basePageUrl) {
        this.driver = driver;
        this.basePageUrl = basePageUrl;
        pageUrl = basePageUrl;
        setElements(driver);
    }

    private void setElements(WebDriverWrapper driver) {
        inputField = new InputField(driver);
        baseElement = new BaseElement(driver);
        dropDown = new DropDown(driver);
    }

}
