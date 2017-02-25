package web.webElements;

import web.utils.TestUtils;
import org.openqa.selenium.By;

/**
 * Created by maxim on 1/28/2017.
 */
public class InputField extends BaseElement {

    public InputField() {
        super();
    }

    public void sendKeys(By by, CharSequence... charSequences) {
        driver.findElement(by).sendKeys(charSequences);
    }

    public void clear(By by) {
        driver.findElement(by).clear();
        //Second try for IE
        if (!driver.findElement(by).getAttribute("value").equalsIgnoreCase("")) {
            driver.findElement(by).clear();
        }
    }

    @Override
    public String getText(By by) {
        return TestUtils.convertToUTF8(driver.findElement(by).getAttribute("value"));
    }

    public String getPlaceholderValue(By by) {
        return driver.findElement(by).getAttribute("placeholder");
    }
}
