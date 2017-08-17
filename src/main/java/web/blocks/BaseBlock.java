package web.blocks;

import web.utils.WebDriverFactory;
import web.utils.WebDriverWrapper;
import web.webElements.BaseElement;
import web.webElements.DropDown;
import web.webElements.InputField;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

/**
 * Created by maxim on 1/28/2017.
 */
public class BaseBlock {
    protected WebDriverWrapper driver;

    protected InputField inputField;
    protected BaseElement baseElement;
    protected DropDown dropDown;

    public BaseBlock(WebDriverWrapper driver)
    {
        this.driver = driver;
        setElements(driver);
    }

    private void setElements(WebDriverWrapper driver){
        inputField = new InputField(driver);
        baseElement = new BaseElement(driver);
        dropDown = new DropDown(driver);
    }

    public void refreshPage(By by){
//        driver.navigate().refresh();
        driver.findElement(by).sendKeys(Keys.F5);
    }
}
