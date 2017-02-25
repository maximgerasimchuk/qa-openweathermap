package web;

import api.utils.ReportWriter;
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

    @Test
    public void testCurrentLocation() {
        HomePage homePage = new HomePage(URL);
        ResultPage resultPage = new ResultPage();
        Header header = new Header();
        homePage.open();
        homePage.fillSearch("Kyiv");
        homePage.search();

        Assert.assertTrue(resultPage.verifySearchRequest(homePage));
        header.goToHomePage();
    }

    @Test
    public void testLinksOnHomePage() {
        HomePage homePage = new HomePage(URL);
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
