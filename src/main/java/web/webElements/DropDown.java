package web.webElements;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by maxim on 1/28/2017.
 */
public class DropDown extends BaseElement{
    public DropDown() {
        super();
    }

    public void selectValue(By caretLocator, By list, By valueListLocator, String valueToSelect) {
        driver.findElement(caretLocator).click();
        driver.waitAndVerifyElementPresent(list);
        List<WebElement> webElementList = driver.findElements(valueListLocator);
        for(WebElement webElement: webElementList){
            if(webElement.getText().equals(valueToSelect)){
                JavascriptExecutor executor = (JavascriptExecutor)driver;
                executor.executeScript("arguments[0].click();", webElement);
                break;
            }
        }
    }

    public void selectValueByIndex(By caretLocator, By list, By valueListLocator, int index) {
        driver.findElement(caretLocator).click();
        driver.waitAndVerifyElementPresent(list);
        List<WebElement> webElementList = driver.findElements(valueListLocator);
        webElementList.get(index).click();
    }
}
