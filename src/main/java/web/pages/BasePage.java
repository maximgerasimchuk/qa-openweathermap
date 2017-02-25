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

    public boolean isOpened()
    {
        ReportWriter.logInfo("'" + pageUrl + "' is opened!");
        return driver.getCurrentUrl().contains(pageUrl);
    }

    public BasePage()
    {
        driver = WebDriverFactory.getDriverWrapper();
        setElements();
    }

    public BasePage(String basePageUrl)
    {
        driver = WebDriverFactory.getDriverWrapper();
        this.basePageUrl = basePageUrl;
        pageUrl = basePageUrl;
        setElements();
    }

    private void setElements(){
        inputField = new InputField();
        baseElement = new BaseElement();
        dropDown = new DropDown();
    }

}
