package web;

import api.utils.ReportWriter;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import web.blocks.Header;
import web.pages.HomePage;
import web.pages.ResultPage;
import web.utils.TestBase;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.List;

/**
 * Created by maxim on 1/28/2017.
 */
public class TestHomePage extends TestBase {
    private HomePage homePage;
    private ResultPage resultPage;
    private Header header;

    private void initPages(){
        homePage = new HomePage(driver, URL);
        resultPage = new ResultPage(driver);
        header = new Header(driver);
    }

    @DataProvider
    public Object[][] cityData(){
        return new Object[][]{
                {"Kiev"},
                {"Kyiv"},
                {"Odessa"},
        };
    }

    @Test(dataProvider = "cityData")
    public void testCurrentWeatherByCity(String city) {
        initPages();
        homePage.open();
        homePage.fillSearch(city);
        homePage.search();
        Assert.assertTrue(resultPage.verifySearchRequest(homePage));

        header.goToHomePage();
    }

    @Test
    public void testLinksOnHomePage() {
        initPages();
        homePage.open();
        List<WebElement> list = homePage.getListOfAllLinks();
        ReportWriter.logInfo("Total number of links on HomePage is: '" + list.size() + "'.");
        for (WebElement element : list) {
            try {
                String response = homePage.isLinkBroken(new URL(element.getAttribute("href")));
                ReportWriter.logInfo("URL: " + element.getAttribute("href") + " returned: " + response);
                Assert.assertEquals(response, "OK");
            } catch (Exception e) {
                ReportWriter.logError("At " + element.getAttribute("innerHTML") + " Exception occured: " + e.getMessage());
            }
        }
    }
}
