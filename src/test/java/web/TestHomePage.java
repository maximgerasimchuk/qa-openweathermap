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
    private ThreadLocal<HomePage> homePage = new ThreadLocal<>();
    private ThreadLocal<ResultPage> resultPage = new ThreadLocal<>();
    private ThreadLocal<Header> header = new ThreadLocal<>();

    private void initPages(){
        homePage.set(new HomePage(getDriver(), URL));
        resultPage.set(new ResultPage(getDriver()));
        header.set(new Header(getDriver()));
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
        homePage.get().open();
        homePage.get().fillSearch(city);
        homePage.get().search();
        Assert.assertTrue(resultPage.get().verifySearchRequest(homePage.get()));

        header.get().goToHomePage();
    }

    @Test
    public void testLinksOnHomePage() {
        initPages();
        homePage.get().open();
        List<WebElement> list = homePage.get().getListOfAllLinks();
        ReportWriter.logInfo("Total number of links on HomePage is: '" + list.size() + "'.");
        for (WebElement element : list) {
            try {
                String response = homePage.get().isLinkBroken(new URL(element.getAttribute("href")));
                ReportWriter.logInfo("URL: " + element.getAttribute("href") + " returned: " + response);
                Assert.assertEquals(response, "OK");
            } catch (Exception e) {
                ReportWriter.logError("At " + element.getAttribute("innerHTML") + " Exception occured: " + e.getMessage());
            }
        }
    }
}
