package web.pages;

import api.utils.ReportWriter;
import org.openqa.selenium.By;
import web.utils.WebDriverWrapper;

/**
 * Created by maxim on 1/28/2017.
 */
public class ResultPage extends BasePage {
    private static final By RESULT_CITY = By.xpath("//table/tbody/tr/td[2]/b[1]/a");

    public ResultPage(WebDriverWrapper driver){
        super(driver);
    }

    public static By getResultCity() {
        return RESULT_CITY;
    }

    public boolean verifySearchRequest(HomePage homePage) {
        String cityExpected = homePage.getCity();
        String cityCountryActual = baseElement.getText(RESULT_CITY);
        ReportWriter.logTestStep("Verify Search Response for city: " + homePage.getCity());
        String cityActual = cityCountryActual.substring(0, cityCountryActual.indexOf(','));
        if (!cityActual.equalsIgnoreCase(cityExpected)) {
            ReportWriter.logError("cityExpected: " + cityExpected);
            ReportWriter.logError("cityActual: " + cityActual);
            return false;
        }
        return true;
    }
}
